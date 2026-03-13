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
fun DreamParticles() {

    val infinite = rememberInfiniteTransition(label = "")

    val progress by infinite.animateFloat(
        0f,
        1f,
        animationSpec = infiniteRepeatable(
            tween(20000),
            RepeatMode.Restart
        ),
        label = ""
    )

    val particles = remember {
        List(40) {
            Pair(Random.nextFloat(), Random.nextFloat())
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {

        particles.forEach {

            val y = (it.second + progress * 0.3f) % 1f

            drawCircle(
                color = Color.White.copy(alpha = 0.25f),
                radius = 5f,
                center = Offset(
                    size.width * it.first,
                    size.height * y
                )
            )
        }
    }
}