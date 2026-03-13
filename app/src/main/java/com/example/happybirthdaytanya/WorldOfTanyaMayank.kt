package com.example.happybirthdaytanya

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WorldOfTanyaMayank(
    onNext: () -> Unit
) {

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val floating by infiniteTransition.animateFloat(
        initialValue = -20f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2500,
                easing = EaseInOutSine
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    BaseScreen {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Enter the world of\nMayank & Tanya 🧿",
                fontSize = 30.sp,
                color = Color.White,
                lineHeight = 40.sp,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.graphicsLayer {
                    translationY = floating
                }
            )

            Spacer(modifier = Modifier.height(60.dp))

            AnimatedArrowButton(
                onClick = onNext
            )
        }
    }
}