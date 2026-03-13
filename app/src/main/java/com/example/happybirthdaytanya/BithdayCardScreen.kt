package com.example.happybirthdaytanya

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*

@Composable
fun BirthdayCardScreen(
    onNext: () -> Unit
) {

    var showNextButton by remember { mutableStateOf(false) }

    val message = """
Take aside that pillow for me 🥹💕

Something is waiting there for you...
Actually I like adding something made with my hands for you,
Something that holds a part of me in physical sense,
Something which can stay with you if ever I leave this world...
So yes these are for you, made with deep dedication....
""".trimIndent()

    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("pillow_bear.lottie")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF6FB))
    ) {

        BlushParticles()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TypeWriterText(
                text = message,
                fontSize = 18,
                lineHeight = 24,
                delayMillis = 90,
                textColor = Color.Black,
                onFinished = {
                    showNextButton = true
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(220.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            if (showNextButton) {

                AnimatedArrowButton(
                    onClick = {
                        onNext()
                    }
                )
            }
        }
    }
}