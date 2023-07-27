package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.network.Book
import com.example.bookshelf.ui.BookshelfMainScreen
import com.example.bookshelf.ui.BookshelfViewModel
import com.example.bookshelf.ui.theme.BookshelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookshelfTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: BookshelfViewModel = viewModel(
                        factory = BookshelfViewModel.Factory
                    )
                    BookshelfMainScreen(
                        viewModel = viewModel,
                        onBackButtonClick = { viewModel.resetSearch() },
                        onCardClick = { Book("","") },
                    )
                }
            }
        }
    }
}
