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
class BookshelfViewModel(private val bookRepository: BookRepository) : ViewModel() {

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
                NetworkUiState.Success(searchResult.items)
            } catch (e: IOException) {
                NetworkUiState.Error
            } catch (e: HttpException) {
                NetworkUiState.Error
            }
        }
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

