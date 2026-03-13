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
fun FloatingCoinParticles() {

    val infinite = rememberInfiniteTransition(label = "")

    val particles = remember {
        List(20) {
            Coin(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                size = Random.nextFloat() * 10f + 8f,
                speed = Random.nextFloat() * 0.2f + 0.05f
            )
        }
    }

    val progress by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(15000),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    Canvas(modifier = Modifier.fillMaxSize()) {

        particles.forEach {

            val yMove = (it.y + progress * it.speed) % 1f

            drawCircle(
                color = Color(0xFFFFD54F).copy(alpha = 0.35f),
                radius = it.size,
                center = Offset(
                    size.width * it.x,
                    size.height * yMove
                )
            )
        }
    }
}

data class Coin(
    val x: Float,
    val y: Float,
    val size: Float,
    val speed: Float
)