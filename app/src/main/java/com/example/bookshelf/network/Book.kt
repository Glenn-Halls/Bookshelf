package com.example.bookshelf.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    @SerialName(value = "selfLink")
    val link: String,
    @SerialName(value = "volumeInfo")
    val bookInfo: BookInfo? = BookInfo()
)
