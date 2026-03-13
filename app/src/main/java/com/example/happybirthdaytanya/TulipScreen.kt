package com.example.happybirthdaytanya

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*

@Composable
fun TulipScreen(
    onNext: () -> Unit
) {

    val infinite = rememberInfiniteTransition(label = "")

    val textScale by infinite.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2600),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFE8F5E9),
                        Color(0xFFFFF0F6),
                        Color(0xFFFFF8E1)
                    )
                )
            )
    ) {

        Spacer(modifier = Modifier.height(100.dp))
        TulipPetalParticles()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp, vertical = 50.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text =
                    "You know what's funny?\n\n" +
                            "People buy flowers from shops.\n" +
                            "But for you… I wanted to grow some myself.\n\n" +
                            "So I made these tulips \n" +
                            "using my own hands 🌷\n\n" +
                            "Because something handmade\n" +
                            "carries a little piece of the person\n" +
                            "who made it.\n\n" +
                            "And now this flower carries\n" +
                            "a little piece of me… for you 💚",
                fontFamily = FontFamily.Cursive,
                fontSize = 27.sp,
                lineHeight = 36.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF2E7D32),
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(textScale)
            )

            Spacer(modifier = Modifier.height(20.dp))

            AnimatedArrowButton(
                onClick = onNext
            )
        }
    }
}