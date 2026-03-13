package com.example.happybirthdaytanya

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun WatchScreen(
    onNext: () -> Unit
) {

    var heartClicked by remember { mutableStateOf(false) }
    var heartReadyForNext by remember { mutableStateOf(false) }

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

    val heartScale by animateFloatAsState(
        targetValue = if (heartClicked) 1.8f else 1f,
        animationSpec = tween(2000),
        finishedListener = {
            if (heartClicked) heartReadyForNext = true
        },
        label = ""
    )

    val watchComp by rememberLottieComposition(
        LottieCompositionSpec.Asset("heart_line.lottie")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFFFF0F6),
                        Color(0xFFFFE4EC),
                        Color(0xFFFFF6FB)
                    )
                )
            )
    ) {

        FloatingTimeParticles()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp)
                .padding(top = 60.dp, bottom = 40.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            AnimatedVisibility(
                visible = !heartClicked,
                exit = fadeOut(animationSpec = tween(1000))
            ) {

                Text(
                    text =
                        "Time is funny you know...\n\n" +
                                "Out of all the seconds, minutes and hours\n" +
                                "in this huge universe,\n\n" +
                                "somehow my favourite moments\n" +
                                "became the ones spent with you.\n\n" +
                                "So I thought I should gift you something\n" +
                                "that reminds you of time…\n\n" +
                                "but more importantly,\n" +
                                "of our time together ⌚💗\n\n" +
                                "and the time that still waits for me and you 💗",

                    fontFamily = FontFamily.Cursive,
                    fontSize = 27.sp,
                    lineHeight = 36.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFFD81B60),
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(textScale)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Box(contentAlignment = Alignment.Center) {

                LottieAnimation(
                    composition = watchComp,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .size(250.dp)
                        .scale(heartScale)
                        .clickable {

                            if (!heartClicked) {
                                heartClicked = true
                            } else if (heartReadyForNext) {
                                onNext()
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            AnimatedVisibility(
                visible = heartClicked,
                enter = fadeIn(animationSpec = tween(1500))
            ) {

                Text(
                    text = "Now put forward your hand for Mayank ❤️",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 30.sp,
                    lineHeight = 34.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFFD81B60)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}