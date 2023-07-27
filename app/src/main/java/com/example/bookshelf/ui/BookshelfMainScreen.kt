package com.example.bookshelf.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bookshelf.R
import com.example.bookshelf.network.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfMainScreen(
    viewModel: BookshelfViewModel,
    onBackButtonClick: () -> Unit,
) {
    // uiState holds all state values needed by subsequent Composables
    val uiState = viewModel.uiState.collectAsState().value
    val networkStatus = viewModel.networkUiState

    Scaffold(
        topBar = {
            BookshelfTopAppBar(
                onUpButtonClick = if (uiState.showBook) {
                    { viewModel.unSelectBook() }
                } else { onBackButtonClick }
            )
        }
    ) { innerPadding ->
        if (!uiState.searchComplete) {
            BookshelfSearchScreen(
                uiState = uiState,
                networkStatus = networkStatus,
                searchStringUpdate = { viewModel.updateSearchQuery(it) },
                onSearchClicked = { viewModel.doSearch() },
                modifier = Modifier.padding(innerPadding)
            )
        } else if (uiState.showBook) {
            BookshelfBookDetails(
                book = uiState.bookSelected ?: Book("", ""),
                onBackPressed = { viewModel.unSelectBook() },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            ResultScreen(
                onBackHandler = onBackButtonClick,
                onTryAgainButton = { viewModel.doSearch() },
                networkStatus = networkStatus,
                viewModel = viewModel,
                topText = uiState.numResults.toString() + uiState.showBook,
                onCardClick = { viewModel.selectBook(it) },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfTopAppBar(onUpButtonClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        }
    )
}

