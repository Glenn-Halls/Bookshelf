package com.example.bookshelf.ui

import com.example.bookshelf.network.Book

data class BookshelfUiState(
    val searchQuery: String = "",
    val searchComplete: Boolean = false,
    val numResults: Int = 0,
    var showBook: Boolean = false,
    val bookSelected: Book?,
    val searchInstructions: String = "Enter a search term to begin your browsing journey!",
    val searchButtonTitle: String = "Browse Books",
)
