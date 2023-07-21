package com.example.bookshelf.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.network.BookshelfApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookshelfViewModel : ViewModel() {

    // Create observable state holder
    private val _uiState = MutableStateFlow(BookshelfUiState())
    // Create accessor to state values
    val uiState: StateFlow<BookshelfUiState> = _uiState
    // Create observable network state holder
    private val _networkUiState = MutableStateFlow(NetworkUiState())
    // Create accessor to network state values
    val networkUiState: StateFlow<NetworkUiState> = _networkUiState


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
        _networkUiState.update {
            it.copy(bookList = "")
        }
    }

    private fun getBooks() {
        viewModelScope.launch {
            val result = BookshelfApi.retrofitService.getBooks()
            _networkUiState.update {
                it.copy(bookList = result)
            }
        }
    }
}
