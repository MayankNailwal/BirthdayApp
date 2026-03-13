package com.example.happybirthdaytanya

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun KitchenScreen(
    onNext: () -> Unit
) {

    var showPassword by remember { mutableStateOf(false) }
    var showNextButton by remember { mutableStateOf(false) }

    val message = """
You know what? 🤭

You seem dehydrated...

Go to the kitchen now !
""".trimIndent()

    BaseScreen {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

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
                    fontSize = 32,
                    lineHeight = 40,
                    delayMillis = 90,
                    onFinished = {
                        showNextButton = true
                    }
                )

                Spacer(modifier = Modifier.height(90.dp))

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
                    correctPassword = "TUMBLER",
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