package com.example.bookshelf.test.fake

import com.example.bookshelf.network.Book
import com.example.bookshelf.network.SearchResult


object FakeDataSource {
    private const val BookName1 = "Book01"
    private const val BookName2 = "Book02"
    private const val BookName3 = "Book03"
    private const val BookLink1 = "BookLink01"
    private const val BookLink2 = "BookLink02"
    private const val BookLink3 = "BookLink03"
    val bookList = listOf(
        Book(BookName1, BookLink1),
        Book(BookName2, BookLink2),
        Book(BookName3, BookLink3),
    )
    // Mimics result from Network containing List of three books
    val searchResult: SearchResult = SearchResult(
        totalItems = bookList.size,
        items = bookList
    )
}
