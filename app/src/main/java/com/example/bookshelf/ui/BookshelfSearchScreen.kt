package com.example.bookshelf.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfSearchScreen(
    uiState: BookshelfUiState,
    searchStringUpdate: (String) -> Unit,
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Text(
                text = "Test Data: " +
                        "\n${uiState.searchInstructions}" +
                        "\n${uiState.searchComplete}" +
                        "\n${uiState.searchQuery}",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

        }
        TextField(
            label = {
                Text(
                    text = "Search Google's Library",
                    style = MaterialTheme.typography.labelMedium
                )
            },
            value = uiState.searchQuery,
            singleLine = true,
            onValueChange = searchStringUpdate,
        )
        Spacer(modifier = modifier.size(16.dp))
        Button(onClick = onSearchClicked) {
            Text(
                text = uiState.searchButtonTitle,
                style = MaterialTheme.typography.labelLarge
            )
        }
        Spacer(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        )
    }
}
