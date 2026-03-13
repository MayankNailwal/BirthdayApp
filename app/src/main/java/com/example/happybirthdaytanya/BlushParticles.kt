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
fun BlushParticles() {

    val particles = remember {
        List(40) {
            BlushParticle(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                size = Random.nextFloat() * 6f + 6f,
                speed = Random.nextFloat() * 0.3f + 0.1f
            )
        }
    }

    val infinite = rememberInfiniteTransition(label = "")

    val floatProgress by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(9000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    val fade by infinite.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Canvas(modifier = Modifier.fillMaxSize()) {

        particles.forEach {

            val yOffset = size.height * it.y - (floatProgress * size.height * it.speed)

            drawCircle(
                color = Color(0xFFFFB6C1).copy(alpha = fade), // blush pink
                radius = it.size,
                center = Offset(
                    size.width * it.x,
                    yOffset % size.height
                )
            )

            drawCircle(
                color = Color.White.copy(alpha = fade * 0.7f),
                radius = it.size / 2,
                center = Offset(
                    size.width * it.x,
                    yOffset % size.height
                )
            )
        }
    }
}

data class BlushParticle(
    val x: Float,
    val y: Float,
    val size: Float,
    val speed: Float
)