package com.example.happybirthdaytanya

import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    isFirstOpen: Boolean,
    onTap: () -> Unit = {}
) {

    LaunchedEffect(Unit) {

        if (isFirstOpen) {
            delay(20000) // 1 minute first time
        } else {
            delay(5000) // 5 sec next times
        }

        onTap()
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("hbd_title.lottie")
    )

    val infiniteTransition = rememberInfiniteTransition(label = "")

    // heart beat animation
    val heartScale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    // levitation animation
    val levitate by infiniteTransition.animateFloat(
        initialValue = -30f,
        targetValue = 30f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Black
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.graphicsLayer {
                    translationY = levitate
                }
            ) {

                Text(
                    text = "Tanya Cutie ",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontFamily = FontFamily.Cursive
                )

                Text(
                    text = "❤️",
                    fontSize = 40.sp,
                    modifier = Modifier.graphicsLayer(
                        scaleX = heartScale,
                        scaleY = heartScale
                    )
                )
            }

            Spacer(Modifier.height(220.dp))

            Text(
                text = "Made with immense love by Mayank, only for you 💕",
                color = Color.White,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}