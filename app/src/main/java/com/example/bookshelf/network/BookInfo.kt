package com.example.bookshelf.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookInfo(
    val title: String? = "Title Not Available",
    @SerialName(value = "publishedDate")
    val date: String? = "Date Not Available",
    val description: String? = "Description not available",
    @SerialName(value = "imageLinks")
    val bookCover: BookCover? = BookCover()
)
