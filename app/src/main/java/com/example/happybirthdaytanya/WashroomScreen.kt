package com.example.happybirthdaytanya

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun WashroomScreen(
    onNext: () -> Unit
) {

    var showPassword by remember { mutableStateOf(false) }
    var showNextButton by remember { mutableStateOf(false) }

    val message = """
Okay, so you must want to go to the washroom, goooo fast !! 

And look what you found there...

Also now from here, each screen comes with a gift but each
next screen requires a password, hint for which is in the previous gift itself...
""".trimIndent()

    BaseScreen {

        Box(modifier = Modifier.fillMaxSize()) {

            FloatingParticles()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                TypeWriterText(
                    text = message,
                    fontSize = 30,
                    lineHeight = 32,
                    delayMillis = 100,
                    onFinished = {
                        showNextButton = true
                    }
                )

                Spacer(modifier = Modifier.height(84.dp))

                if (showNextButton) {

                    AnimatedArrowButton(
                        onClick = {
                            showPassword = true
                        }
                    )
                }
            }

            if (showPassword) {

                PasswordDialog(
                    correctPassword = "MIRROR",
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
}