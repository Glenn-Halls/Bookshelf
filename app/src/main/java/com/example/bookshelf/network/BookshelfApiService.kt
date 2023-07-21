package com.example.bookshelf.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.googleapis.com"
private const val BOOKSHELF_ENDPOINT = "books/v1/volumes?q=jazz+history"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface BookshelfApiService {
    @GET(BOOKSHELF_ENDPOINT)
    suspend fun getBooks(): String
}

object BookshelfApi {
    val retrofitService : BookshelfApiService by lazy {
        retrofit.create(BookshelfApiService::class.java)
    }
}
