package com.example.happybirthdaytanya

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GetReadyScreen(
    onNext: () -> Unit
) {
    var showPassword by remember { mutableStateOf(false) }

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val message = """
                Now go, get ready cutu we are getting late, and hn press this next button once you are ready, password is with me, hn lekin merko pyaar se puchna padega password ekdum ekdum ekdum zyada pyaar se 🥰
                
                And I have a very big reveal for you on the next screen !
            """.trimIndent()
            Text(
                text = message,
                fontFamily = FontFamily.Cursive,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color(0xFFFFA6C1), // blush pink
                lineHeight = 34.sp
            )

            Spacer(modifier = Modifier.height(50.dp))

            AnimatedArrowButton(
                onClick = {
                    showPassword = true
                }
            )
        }

        if (showPassword) {

            PasswordDialog(
                correctPassword = "JAI GURUJI",
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