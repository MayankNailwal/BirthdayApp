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
fun NailsScreen(
    onNext: () -> Unit
) {

    val infinite = rememberInfiniteTransition(label = "")

    val textScale by infinite.animateFloat(
        initialValue = 0.97f,
        targetValue = 1.03f,
        animationSpec = infiniteRepeatable(
            animation = tween(2600),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val nailsComp by rememberLottieComposition(
        LottieCompositionSpec.Asset("nails.lottie")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFFFF0F6),
                        Color(0xFFFFE4EC),
                        Color(0xFFFFF8FB)
                    )
                )
            )
    ) {

        SparkleParticles()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .padding(top = 70.dp, bottom = 40.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text =
                    "Okay madam 💅\n\n" +
                            "A birthday is incomplete\n" +
                            "without pretty nails.\n\n" +
                            "So today Mayank\n" +
                            "is officially sponsoring\n" +
                            "your nail extensions 😌✨\n\n" +
                            "Get ready to flex those hands\n" +
                            "like the queen you are 👑",

                fontFamily = FontFamily.Cursive,
                fontSize = 27.sp,
                lineHeight = 36.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFFD81B60),
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(textScale)
            )

            LottieAnimation(
                composition = nailsComp,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(280.dp)
            )

            AnimatedArrowButton(
                onClick = onNext
            )
        }
    }
}