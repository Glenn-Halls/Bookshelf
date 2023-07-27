package com.example.bookshelf.data

import com.example.bookshelf.network.BookshelfApiService
import com.example.bookshelf.network.SearchResult

interface BookRepository {
    suspend fun getBooks(): SearchResult
}

class NetworkBookRepository(
    private val bookApiService: BookshelfApiService,
    private val string: String
) : BookRepository{
    override suspend fun getBooks(): SearchResult = bookApiService.getBooks(string, "40")
}
