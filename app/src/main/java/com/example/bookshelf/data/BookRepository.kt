package com.example.bookshelf.data

import com.example.bookshelf.network.BookshelfApi
import com.example.bookshelf.network.SearchResult

interface BookRepository {
    suspend fun getBooks(): SearchResult
}

class NetworkBookRepository() : BookRepository{
    override suspend fun getBooks(): SearchResult {
        return BookshelfApi.retrofitService.getBooks()
    }
}
