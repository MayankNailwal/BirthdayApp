package com.example.happybirthdaytanya


import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun FloatingTimeParticles() {

    val infinite = rememberInfiniteTransition(label = "")

    val particles = remember {
        List(25) {
            Particle(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                size = Random.nextFloat() * 12f + 6f,
                speed = Random.nextFloat() * 0.3f + 0.1f
            )
        }
    }

    val offset by infinite.animateFloat(
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

            val yMove = (it.y + offset * it.speed) % 1f

            drawCircle(
                color = Color(0x33FF4081),
                radius = it.size,
                center = androidx.compose.ui.geometry.Offset(
                    x = size.width * it.x,
                    y = size.height * yMove
                )
            )
        }
    }
}

data class Particle(
    val x: Float,
    val y: Float,
    val size: Float,
    val speed: Float
)