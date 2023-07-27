package com.example.bookshelf.network

import retrofit2.http.GET
import retrofit2.http.Query


interface BookshelfApiService {
    @GET("books/v1/volumes")
    suspend fun getBooks(
        @Query("q") string: String,
        @Query("maxResults") results: String,
    ): SearchResult
}
