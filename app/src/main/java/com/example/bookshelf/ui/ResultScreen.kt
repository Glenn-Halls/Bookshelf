package com.example.bookshelf.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bookshelf.network.Book
import kotlinx.coroutines.delay

@Composable
fun ResultScreen(
    onBackHandler: () -> Unit,
    onTryAgainButton: () -> Unit,
    networkStatus: NetworkUiState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        BackHandler(onBack = onBackHandler)
        when (networkStatus) {
            is NetworkUiState.Error -> ErrorScreen(onTryAgainButton)
            is NetworkUiState.Loading -> LoadingScreen()
            is NetworkUiState.Success -> SuccessScreen(
                networkStatus.searchResult.size.toString(),
                bookList = networkStatus.searchResult
            )
        }
    }
}

@Composable
fun SuccessScreen(
    topText: String,
    bookList: List<Book>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(
            text = "Your search yielded $topText results:\n",
        )
        bookList.forEach {
            Text(
                text = it.bookInfo!!.title.toString()
            )
            Text(
                text = it.bookInfo.date.toString()
            )
            Text(
                text = it.bookInfo.description.toString()
            )
            Text(
                text = it.bookInfo.bookCover!!.thumbnail.toString()
            )
            Text(
                text = "\n"
            )
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        LoadingAnimation3()
    }
}

@Composable
fun ErrorScreen(
    tryAgainButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                modifier = Modifier.size(90.dp)
            )
            Text(
                text = "ERROR:\nSomething went wrong...",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = tryAgainButton) {
                Text(
                    text = "Try Again",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
fun LoadingAnimation3(
    circleColor: Color = MaterialTheme.colorScheme.primary,
    circleSize: Dp = 36.dp,
    animationDelay: Int = 400,
    initialAlpha: Float = 0.3f
) {
    // 3 circles
    val circles = listOf(
        remember {
            Animatable(initialValue = initialAlpha)
        },
        remember {
            Animatable(initialValue = initialAlpha)
        },
        remember {
            Animatable(initialValue = initialAlpha)
        }
    )

    circles.forEachIndexed { index, animatable ->

        LaunchedEffect(Unit) {

            // Use coroutine delay to sync animations
            delay(timeMillis = (animationDelay / circles.size).toLong() * index)

            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDelay
                    ),
                    repeatMode = RepeatMode.Reverse
                )
            )
        }
    }
    // container for circles
    Row(
        modifier = Modifier
        //.border(width = 2.dp, color = Color.Magenta)
    ) {

        // adding each circle
        circles.forEachIndexed { index, animatable ->

            // gap between the circles
            if (index != 0) {
                Spacer(modifier = Modifier.width(width = 6.dp))
            }

            Box(
                modifier = Modifier
                    .size(size = circleSize)
                    .clip(shape = CircleShape)
                    .background(
                        color = circleColor
                            .copy(alpha = animatable.value)
                    )
            ) {
            }
        }
    }
}
