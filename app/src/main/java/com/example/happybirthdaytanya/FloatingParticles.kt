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
fun FloatingParticles() {

    val stars = remember {
        List(60) {
            Star(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                size = Random.nextFloat() * 3f + 2f
            )
        }
    }

    val infinite = rememberInfiniteTransition(label = "")

    // twinkle for background stars
    val twinkle by infinite.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    // ⭐ yellow twinkle for shooting stars
    val yellowTwinkle by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(600),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val shootingColor = Color.White.copy(
        red = 1f,
        green = 1f - (0.3f * yellowTwinkle),
        blue = 1f - (0.8f * yellowTwinkle)
    )

    // shooting stars progress
    val star1 by infinite.animateFloat(
        0f, 1f,
        infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing)
        ), label = ""
    )

    val star2 by infinite.animateFloat(
        0f, 1f,
        infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing, delayMillis = 1500)
        ), label = ""
    )

    val star3 by infinite.animateFloat(
        0f, 1f,
        infiniteRepeatable(
            animation = tween(4500, easing = LinearEasing, delayMillis = 3000)
        ), label = ""
    )

    val star4 by infinite.animateFloat(
        0f, 1f,
        infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing, delayMillis = 4500)
        ), label = ""
    )

    Canvas(modifier = Modifier.fillMaxSize()) {

        // background twinkling stars
        stars.forEach {
            drawCircle(
                color = Color.White.copy(alpha = twinkle),
                radius = it.size,
                center = Offset(
                    size.width * it.x,
                    size.height * it.y
                )
            )
        }

        drawShootingStar(size, star1, 0.1f, shootingColor)
        drawShootingStar(size, star2, 0.3f, shootingColor)
        drawShootingStar(size, star3, 0.5f, shootingColor)
        drawShootingStar(size, star4, 0.7f, shootingColor)
    }
}

fun androidx.compose.ui.graphics.drawscope.DrawScope.drawShootingStar(
    size: androidx.compose.ui.geometry.Size,
    progress: Float,
    heightFactor: Float,
    color: Color
) {

    val x = size.width * progress
    val y = size.height * heightFactor + progress * 150f

    drawLine(
        color = color,
        start = Offset(x, y),
        end = Offset(x - 120f, y - 60f),
        strokeWidth = 4f
    )

    drawLine(
        color = color.copy(alpha = 0.4f),
        start = Offset(x, y),
        end = Offset(x - 200f, y - 100f),
        strokeWidth = 7f
    )
}

data class Star(
    val x: Float,
    val y: Float,
    val size: Float
)