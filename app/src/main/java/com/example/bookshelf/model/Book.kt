package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val title: String,
    val authors: String,
    val datePublished: String,
    val description: String,
    val imgSrc: String,
)
