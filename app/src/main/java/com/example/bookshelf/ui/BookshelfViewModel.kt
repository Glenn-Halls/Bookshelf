package com.example.bookshelf.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class BookshelfViewModel : ViewModel() {

    // Create observable state holder
    private val _uiState = MutableStateFlow(BookshelfUiState())
    // Create accessor to state values
    val uiState: StateFlow<BookshelfUiState> = _uiState

    fun updateSearchQuery(query: String) {
        _uiState.update {
            it.copy(searchQuery = query)
        }
    }

    fun doSearch() {
        _uiState.update {
            it.copy(searchComplete = true)
        }
    }
}
