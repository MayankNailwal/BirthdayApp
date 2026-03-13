package com.example.happybirthdaytanya

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun PasswordDialog(
    correctPassword: String,
    onCorrect: () -> Unit,
    onDismiss: () -> Unit
) {

    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    val infinite = rememberInfiniteTransition(label = "")

    val glow by infinite.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val scale by infinite.animateFloat(
        initialValue = 0.95f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    Dialog(onDismissRequest = onDismiss) {

        Box(
            modifier = Modifier
                .scale(scale)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xFF0F0F0F),
                            Color(0xFF1A1A1A)
                        )
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
                .border(
                    width = 2.dp,
                    brush = Brush.linearGradient(
                        listOf(
                            Color(0xFFFFD700).copy(alpha = glow),
                            Color.White.copy(alpha = 0.3f)
                        )
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(28.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "🔐 Secret Gift Locked",
                    color = Color.White,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Guess the password to unlock the next surprise",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                        error = false
                    },
                    placeholder = { Text("Enter Password") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF1A1A1A),
                        unfocusedContainerColor = Color(0xFF1A1A1A),
                        focusedIndicatorColor = Color(0xFFFFD700),
                        unfocusedIndicatorColor = Color.Gray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                if (error) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Wrong password ❌",
                        color = Color.Red
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                Button(
                    onClick = {

                        if (password.trim().uppercase() ==
                            correctPassword.uppercase()
                        ) {
                            onCorrect()
                        } else {
                            error = true
                        }

                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD700)
                    )
                ) {

                    Text(
                        "Unlock Gift 🎁",
                        color = Color.Black
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                TextButton(onClick = onDismiss) {

                    Text(
                        "Cancel",
                        color = Color.Gray
                    )

                }
            }
        }
    }
}