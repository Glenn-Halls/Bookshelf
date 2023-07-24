package com.example.bookshelf.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookItem(
    val id: String,
    @SerialName(value = "selfLink")
    val link: String,
)
