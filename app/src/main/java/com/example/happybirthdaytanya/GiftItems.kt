package com.example.happybirthdaytanya

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun GiftItems() {

    val infinite = rememberInfiniteTransition(label = "")

    val float by infinite.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val glow by infinite.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "🧴 Face Wipes",
            fontSize = 24.sp,
            color = Color.White.copy(alpha = glow),
            modifier = Modifier.offset(y = float.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "🧖‍♀️ Face Sheet Masks",
            fontSize = 24.sp,
            color = Color.White.copy(alpha = glow),
            modifier = Modifier.offset(y = (-float).dp)
        )
    }
}