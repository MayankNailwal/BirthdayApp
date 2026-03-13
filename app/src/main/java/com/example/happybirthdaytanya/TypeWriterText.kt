package com.example.happybirthdaytanya

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TypeWriterText(
    text: String,
    fontSize: Int = 26,
    lineHeight: Int = 36,
    delayMillis: Long = 60,
    onFinished: (() -> Unit)? = null,
    textColor: Color = Color.White
) {

    var visibleText by remember { mutableStateOf("") }

    LaunchedEffect(text) {

        visibleText = ""

        text.forEachIndexed { index, _ ->
            visibleText = text.substring(0, index + 1)
            delay(delayMillis)
        }

        onFinished?.invoke()
    }

    Text(
        text = visibleText,
        color = textColor,
        fontSize = fontSize.sp,
        lineHeight = lineHeight.sp,
        fontFamily = FontFamily.Monospace,
    )
}