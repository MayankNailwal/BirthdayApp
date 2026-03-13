package com.example.happybirthdaytanya

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun RomanticHeartsParticles() {

    val hearts = remember {
        List(35) {
            HeartParticle(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                size = Random.nextFloat() * 8f + 8f,
                speed = Random.nextFloat() * 0.3f + 0.1f
            )
        }
    }

    val infinite = rememberInfiniteTransition(label = "")

    val floatProgress by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(9000, easing = LinearEasing)
        ),
        label = ""
    )

    val fade by infinite.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Canvas(modifier = Modifier.fillMaxSize()) {

        hearts.forEach {

            val yOffset =
                size.height * it.y - (floatProgress * size.height * it.speed)

            drawCircle(
                color = Color(0xFFFF6FA5).copy(alpha = fade),
                radius = it.size,
                center = Offset(
                    size.width * it.x,
                    yOffset % size.height
                )
            )

            drawCircle(
                color = Color.White.copy(alpha = fade * 0.6f),
                radius = it.size / 2,
                center = Offset(
                    size.width * it.x,
                    yOffset % size.height
                )
            )
        }
    }
}

data class HeartParticle(
    val x: Float,
    val y: Float,
    val size: Float,
    val speed: Float
)