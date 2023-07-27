package com.example.bookshelf.ui

data class BookshelfUiState(
    val searchQuery: String = "",
    val searchComplete: Boolean = false,
    val numResults: Int = 0,
    val searchInstructions: String = "Search below",
    val searchButtonTitle: String = "Browse Books",
)
