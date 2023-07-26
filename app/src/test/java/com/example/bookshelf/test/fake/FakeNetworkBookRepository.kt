package com.example.bookshelf.test.fake

import com.example.bookshelf.data.BookRepository
import com.example.bookshelf.network.SearchResult

class FakeNetworkBookRepository : BookRepository {
    override suspend fun getBooks(): SearchResult {
        return FakeDataSource.searchResult
    }
}
