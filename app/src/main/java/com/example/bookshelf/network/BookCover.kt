package com.example.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class BookCover(
    val smallThumbnail: String? = "https://i.imgur.com/YjoNXCX.png",
    val thumbnail: String? = "https://i.imgur.com/YjoNXCX.png",
)
