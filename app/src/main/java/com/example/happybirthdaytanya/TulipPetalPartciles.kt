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
fun TulipPetalParticles() {

    val infinite = rememberInfiniteTransition(label = "")

    val particles = remember {
        List(25) {
            Petal(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                size = Random.nextFloat() * 10f + 6f,
                speed = Random.nextFloat() * 0.25f + 0.05f
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
                color = Color(0xFFFF80AB).copy(alpha = 0.4f),
                radius = it.size,
                center = Offset(
                    size.width * it.x,
                    size.height * yMove
                )
            )
        }
    }
}

data class Petal(
    val x: Float,
    val y: Float,
    val size: Float,
    val speed: Float
)