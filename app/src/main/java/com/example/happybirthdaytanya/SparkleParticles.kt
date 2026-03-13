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
fun SparkleParticles() {

    val infinite = rememberInfiniteTransition(label = "")

    val particles = remember {
        List(25) {
            Sparkle(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                size = Random.nextFloat() * 6f + 3f,
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
                color = Color(0xFFFF80AB).copy(alpha = 0.35f),
                radius = it.size,
                center = Offset(
                    size.width * it.x,
                    size.height * yMove
                )
            )
        }
    }
}

data class Sparkle(
    val x: Float,
    val y: Float,
    val size: Float,
    val speed: Float
)