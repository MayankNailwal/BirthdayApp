package com.example.happybirthdaytanya

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GoodMorningScreen(
    onNext: () -> Unit = {}
) {

    val message = """
How special this day is for me,
I can't even tell you.

I wish I could pause time for us.

And if life exists again and again,
I would still want to wake up
and say...

Good Morning Tanya.
""".trimIndent()

    BaseScreen {

        FloatingParticles()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Good Morning Tanya ☀️",
                fontSize = 24.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(30.dp))

            TypeWriterText(
                text = message,
                fontSize = 22,
                lineHeight = 24
            )

            Spacer(modifier = Modifier.height(50.dp))

            AnimatedArrowButton(
                onClick = onNext
            )
        }
    }
}