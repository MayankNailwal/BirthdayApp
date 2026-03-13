package com.example.happybirthdaytanya

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlushToyScreen(
    onNext: () -> Unit
) {

    var showPassword by remember { mutableStateOf(false) }

    val infinite = rememberInfiniteTransition(label = "")

    val textScale by infinite.animateFloat(
        initialValue = 0.96f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFF0F6),
                        Color(0xFFFFE4EC),
                        Color(0xFFFFF6FB)
                    )
                )
            )
    ) {

        // Blush particles
        BlushParticles()

        // Soft floating hearts
        RomanticHeartsParticles()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Hey, I have assigned a sleeping partner for you.\n\nWhenever you feel afraid at night,\njust hug this kitty and remember\nthat you can call Mayank ☺️",
                fontFamily = FontFamily.Cursive,
                fontSize = 30.sp,
                lineHeight = 38.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFFD81B60),
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(textScale)
            )

            Spacer(modifier = Modifier.height(70.dp))

            AnimatedArrowButton(
                onClick = {
                    showPassword = true
                }
            )
        }

        if (showPassword) {

            PasswordDialog(
                correctPassword = "HELLO KITTY",
                onCorrect = {
                    showPassword = false
                    onNext()
                },
                onDismiss = {
                    showPassword = false
                }
            )
        }
    }
}