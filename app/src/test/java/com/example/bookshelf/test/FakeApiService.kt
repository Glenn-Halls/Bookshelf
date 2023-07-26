package com.example.bookshelf.test

import com.example.bookshelf.network.BookshelfApiService
import com.example.bookshelf.network.SearchResult

class FakeApiService : BookshelfApiService {
    override suspend fun getBooks(): SearchResult {
        return FakeDataSource.searchResult
    }
}
