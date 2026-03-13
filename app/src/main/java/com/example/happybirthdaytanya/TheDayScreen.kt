package com.example.happybirthdaytanya

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TheDayScreen(
    onNext: () -> Unit
) {

    val context = LocalContext.current
    val activity = context as Activity

    var stage by remember { mutableStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF0F2027),
                        Color(0xFF203A43),
                        Color(0xFF2C5364)
                    )
                )
            )
    ) {

        DreamParticles()

        when (stage) {

            // ---------------- STAGE 1 ----------------

            1 -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TypeWriterText(
                        text =
                            "So this was everything\n" +
                                    "for the home Tanya 🏡\n\n" +
                                    "But birthdays aren't meant\n" +
                                    "to stay inside four walls.\n\n" +
                                    "Now let's step outside together\n" +
                                    "and celebrate your day\n" +
                                    "the way it truly deserves.\n\n" +
                                    "But wait…\n\n" +
                                    "before we go to the next screen ❤️",
                        delayMillis = 45
                    )
                }

                AnimatedArrowButton(
                    onClick = { stage = 2 },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 40.dp)
                )
            }

            // ---------------- STAGE 2 (VIDEO) ----------------

            2 -> {
                MusicPlayer.mediaPlayer?.pause()
                VideoPlayer(
                    modifier = Modifier.fillMaxSize(),
                    onVideoFinished = {
                        stage = 3
                    }
                )
            }

            // ---------------- STAGE 3 ----------------

            3 -> {
                if (MusicPlayer.mediaPlayer?.isPlaying() == true) return else MusicPlayer.mediaPlayer?.start()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TypeWriterText(
                        text =
                            "Tanya…\n\n" +
                                    "Out of all the days\n" +
                                    "that exist in time,\n" +
                                    "this one is my favorite.\n\n" +
                                    "Because this is the day\n" +
                                    "the world received you.\n\n" +
                                    "And somehow…\n" +
                                    "life decided\n" +
                                    "to bring you into mine.\n\n" +
                                    "Every laugh, every moment,\n" +
                                    "every memory with you\n" +
                                    "is something I carry\n" +
                                    "in the most special corner\n" +
                                    "of my heart.\n\n" +
                                    "Happy Birthday Tanya ❤️\n\n" +
                                    "— Mayank",
                        delayMillis = 70,
                        fontSize = 18
                    )
                }

                AnimatedArrowButton(
                    onClick = {
                        AppState.resetApp(context)
                        onNext()
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 40.dp)
                )
            }
        }
    }
}