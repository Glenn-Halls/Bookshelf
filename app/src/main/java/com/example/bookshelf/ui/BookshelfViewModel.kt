package com.example.bookshelf.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BookRepository
import com.example.bookshelf.data.DefaultAppContainer
import com.example.bookshelf.data.NetworkBookRepository
import com.example.bookshelf.network.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface NetworkUiState {
    data class Success(val searchResult: List<Book>) : NetworkUiState
    object Error : NetworkUiState
    object Loading : NetworkUiState
}
class BookshelfViewModel(private var bookRepository: BookRepository) : ViewModel() {

    // Create observable state holder
    private val _uiState = MutableStateFlow(BookshelfUiState())

    // Create accessor to state values
    val uiState: StateFlow<BookshelfUiState> = _uiState

    // Create observable network state holder
    var networkUiState: NetworkUiState by mutableStateOf(NetworkUiState.Loading)
        private set


    fun updateSearchQuery(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
        bookRepository = NetworkBookRepository(
            bookApiService = DefaultAppContainer().retrofitService,
            string = query,
        )
    }

    fun doSearch() {
        _uiState.update {
            it.copy(searchComplete = true)
        }
        getBooks()
    }

    fun resetSearch() {
        _uiState.update {
            it.copy(
                searchComplete = false,
                searchQuery = "",
            )
        }
        networkUiState = NetworkUiState.Loading
    }

    fun getBooks() {
        viewModelScope.launch {
            networkUiState = NetworkUiState.Loading
            networkUiState = try{
                val searchResult = bookRepository.getBooks()
                _uiState.update {
                    it.copy(
                        numResults = searchResult.totalItems
                    )
                }
                NetworkUiState.Success(searchResult.items)
            } catch (e: IOException) {
                NetworkUiState.Error
            } catch (e: HttpException) {
                NetworkUiState.Error
            }
        }
    }

    fun getCoilUrl(book: Book): String {
        val oldUrl: String = book.bookInfo!!.bookCover!!.thumbnail.toString()
        return oldUrl.replace("http://", "https://")
    }

    fun getBriefDescription(book: Book) : String {
        val originalDescription: String = book.bookInfo!!.description ?: "Description not available"
        return if (originalDescription.length > 200) {
            originalDescription
                .take(197)
                .replaceAfterLast(' ', "")
                .dropLast(1) + "..."
        } else originalDescription
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val bookRepository = application.container.bookRepository
                BookshelfViewModel(bookRepository = bookRepository)
            }
        }
    }
}

