package com.example.bookshelf.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ResultScreen(
    onBackHandler: () -> Unit,
    displayText: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        BackHandler(enabled = true, onBack = onBackHandler)
        Text(
            text = displayText
        )
    }
}
