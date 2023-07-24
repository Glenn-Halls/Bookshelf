package com.example.bookshelf.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookInfo(
    val title: String,
    @SerialName(value = "publishedDate")
    val date: String,
    val description: String,
)
