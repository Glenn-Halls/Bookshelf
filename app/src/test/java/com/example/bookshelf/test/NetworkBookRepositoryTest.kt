package com.example.bookshelf.test

import com.example.bookshelf.data.NetworkBookRepository
import com.example.bookshelf.test.fake.FakeApiService
import com.example.bookshelf.test.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkBookRepositoryTest {
    @Test
    fun networkBookRepository_getBooks_verifyBookList() =
        runTest {
            val repository = NetworkBookRepository(
                bookApiService = FakeApiService()
            )
            assertEquals(
                repository.getBooks(),
                FakeDataSource.searchResult
            )
        }
}
