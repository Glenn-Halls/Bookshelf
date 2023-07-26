package com.example.bookshelf.network

import retrofit2.http.GET


interface BookshelfApiService {
    @GET("books/v1/volumes?q=jazz+history")
    suspend fun getBooks(): SearchResult
}
