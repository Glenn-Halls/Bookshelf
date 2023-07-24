package com.example.bookshelf.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.network.BookshelfApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface NetworkUiState {
    data class Success(val bookList: String) : NetworkUiState
    object Error : NetworkUiState
    object Loading : NetworkUiState
}
class BookshelfViewModel : ViewModel() {

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

    private fun getBooks() {
        viewModelScope.launch {
            networkUiState = NetworkUiState.Loading
            networkUiState = try{
                val searchResult = BookshelfApi.retrofitService.getBooks()
                NetworkUiState.Success(
                    "Success, ${searchResult.totalItems} results found."
                )
            } catch (e: IOException) {
                NetworkUiState.Error
            } catch (e: HttpException) {
                NetworkUiState.Error
            }
        }
    }
}

