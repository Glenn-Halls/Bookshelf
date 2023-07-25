package com.example.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(
    val totalItems: Int,
    val items: List<Book>
)
