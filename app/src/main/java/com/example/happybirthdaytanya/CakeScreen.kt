package com.example.happybirthdaytanya

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.airbnb.lottie.compose.*

enum class CakeStage {
    INTRO,
    COMEON,
    HORN,
    MAYANK_HEART,
    HBD
}

@Composable
fun CakeScreen(
    onNext: () -> Unit
) {

    var stage by remember { mutableStateOf(CakeStage.INTRO) }

    var launchArrow by remember { mutableStateOf(false) }
    var showHearts by remember { mutableStateOf(false) }

    val arrowY by animateFloatAsState(
        targetValue = if (launchArrow) -1200f else 0f,
        animationSpec = tween(
            durationMillis = 1200,
            easing = FastOutSlowInEasing
        ),
        finishedListener = {
            if (launchArrow) {
                showHearts = true
            }
        },
        label = ""
    )

    val comeonComp by rememberLottieComposition(
        LottieCompositionSpec.Asset("comeon.lottie")
    )

    val hornComp by rememberLottieComposition(
        LottieCompositionSpec.Asset("horn_blow.lottie")
    )

    val heartComp by rememberLottieComposition(
        LottieCompositionSpec.Asset("mayank_heart.lottie")
    )

    val hbdComp by rememberLottieComposition(
        LottieCompositionSpec.Asset("hbd_title.lottie")
    )

    val confettiComp by rememberLottieComposition(
        LottieCompositionSpec.Asset("confetti.lottie")
    )

    val heartsBurstComp by rememberLottieComposition(
        LottieCompositionSpec.Asset("red_heart_man.lottie")
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

        BlushParticles()


        AnimatedVisibility(
            visible = stage == CakeStage.INTRO,
            exit = slideOutVertically { -it }
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Oye Tanyaaa we haven't celebrated yet yaar,\nlet us get rollin 😄",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 30.sp,
                    lineHeight = 36.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFFD81B60)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Follow me everyone!",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFFD81B60)
                )

                Spacer(modifier = Modifier.height(50.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Follow Mayank",
                        color = Color(0xFFD81B60),
                        fontSize = 22.sp,
                        fontFamily = FontFamily.Cursive
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Box(
                        modifier = Modifier.graphicsLayer {
                            translationY = arrowY
                        }
                    ) {

                        AnimatedArrowButton(
                            onClick = {
                                launchArrow = true
                            }
                        )
                    }
                }
            }
        }

        if (showHearts && stage == CakeStage.INTRO) {

            LottieAnimation(
                composition = heartsBurstComp,
                iterations = 1,
                modifier = Modifier.fillMaxSize()
            )

            LaunchedEffect(showHearts) {
                delay(1200)

                showHearts = false   // remove heart animation
                stage = CakeStage.COMEON
            }
        }

        // MESSAGE BASED ON STAGE
        val message = when(stage) {
            CakeStage.COMEON -> "Hey Come everyone and celebrate with me and cutu"
            CakeStage.HORN -> "Just Mayank blowing the horn, he is super excited LOL"
            CakeStage.MAYANK_HEART -> "Wait while the cake comes, i hope the day is going good and am able to find the way to your heart hehehehe"
            else -> ""
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // TEXT MESSAGE
            Text(
                text = message,
                color = Color(0xFFFFA6C1), // blush pink
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Cursive, // cursive text
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            )

            Box(
                modifier = Modifier.size(350.dp),
                contentAlignment = Alignment.Center
            ) {

                when(stage) {
                    CakeStage.COMEON -> {
                        LaunchedEffect(Unit) {
                            delay(10000)
                            stage = CakeStage.HORN
                        }
                        LottieAnimation(
                            composition = comeonComp,
                            iterations = LottieConstants.IterateForever,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    CakeStage.HORN -> {
                        LaunchedEffect(Unit) {
                            delay(10000)
                            stage = CakeStage.MAYANK_HEART
                        }
                        LottieAnimation(
                            composition = hornComp,
                            iterations = LottieConstants.IterateForever,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    CakeStage.MAYANK_HEART -> {
                        LaunchedEffect(Unit) {
                            delay(20000)
                            stage = CakeStage.HBD
                        }
                        LottieAnimation(
                            composition = heartComp,
                            iterations = LottieConstants.IterateForever,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    else -> {}
                }
            }
        }

        if (stage == CakeStage.HBD) {

            LottieAnimation(
                composition = hbdComp,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(350.dp)
            )

            LottieAnimation(
                composition = confettiComp,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AnimatedArrowButton(
                    onClick = onNext
                )
            }
        }
    }
}