package com.example.happybirthdaytanya

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
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
fun WalletScreen(
    onNext: () -> Unit
) {

    var walletClicked by remember { mutableStateOf(false) }

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

    val walletScale by animateFloatAsState(
        targetValue = if (walletClicked) 1.25f else 1f,
        animationSpec = tween(1200),
        label = ""
    )

    val walletComp by rememberLottieComposition(
        LottieCompositionSpec.Asset("wallet_gift.lottie")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFFFF3E0),
                        Color(0xFFFFE0B2),
                        Color(0xFFFFF8E1)
                    )
                )
            )
    ) {

        FloatingCoinParticles()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AnimatedVisibility(
                visible = !walletClicked,
                exit = fadeOut()
            ) {

                Text(
                    text =
                        "A wallet usually carries money,\n" +
                                "cards and small things of life.\n\n" +
                                "But today I'm giving you one\n" +
                                "for a different reason.\n\n" +
                                "Because every time you open it,\n" +
                                "I want you to remember\n" +
                                "that someone out there\n" +
                                "is keeping you\n" +
                                "in the most valuable place\n" +
                                "of his life ❤️",

                    fontFamily = FontFamily.Cursive,
                    fontSize = 27.sp,
                    lineHeight = 36.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF6D4C41),
                    modifier = Modifier
                        .fillMaxWidth()
                        .scale(textScale)
                )
            }

            Box(
                contentAlignment = Alignment.Center
            ) {

                LottieAnimation(
                    composition = walletComp,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .size(260.dp)
                        .scale(walletScale)
                        .clickable {

                            if (!walletClicked) {
                                walletClicked = true
                            } else {
                                onNext()
                            }
                        }
                )
            }

            AnimatedVisibility(
                visible = walletClicked,
                enter = fadeIn(animationSpec = tween(1500))
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text =
                            "Now this wallet will carry\n" +
                                    "something more valuable than money…\n\n" +
                                    "my love for you 💌",

                        fontFamily = FontFamily.Cursive,
                        fontSize = 28.sp,
                        lineHeight = 34.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF5D4037)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    AnimatedArrowButton(
                        onClick = onNext
                    )
                }
            }
        }
    }
}