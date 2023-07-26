package com.example.bookshelf.test

import com.example.bookshelf.test.fake.FakeDataSource
import com.example.bookshelf.test.fake.FakeNetworkBookRepository
import com.example.bookshelf.test.rules.TestDispatcherRule
import com.example.bookshelf.ui.BookshelfViewModel
import com.example.bookshelf.ui.NetworkUiState
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BookshelfViewModelTest {
    @get : Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun viewModel_getBooks_verifyUiStateSuccess() =
        runTest {
            val viewModel = BookshelfViewModel(
                bookRepository = FakeNetworkBookRepository()
            )
            assertEquals(
                viewModel.networkUiState,
                NetworkUiState.Success(FakeDataSource.bookList)
            )
        }
}
