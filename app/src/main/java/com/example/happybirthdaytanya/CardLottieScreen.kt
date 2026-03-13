package com.example.happybirthdaytanya

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import java.io.File
import java.io.FileOutputStream

@Composable
fun CardLottieScreen(
    onNext: () -> Unit
) {

    var showPhotoDialog by remember { mutableStateOf(false) }

    val cardComposition by rememberLottieComposition(
        LottieCompositionSpec.Asset("bday_card.lottie")
    )

    val confettiComposition by rememberLottieComposition(
        LottieCompositionSpec.Asset("confetti.lottie")
    )

    val cardProgress by animateLottieCompositionAsState(
        composition = cardComposition,
        iterations = LottieConstants.IterateForever,
        speed = 0.4f
    )

    val infinite = rememberInfiniteTransition(label = "")

    val titleScale by infinite.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val context = LocalContext.current

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->

        if (bitmap != null) {

            val fileSaved = saveBitmap(context, bitmap)

            if (fileSaved) {
                onNext()
            }
        }
    }


    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->

        if (granted) {
            cameraLauncher.launch(null)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF6FB))
    ) {

        RomanticHeartsParticles()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Yes, I made this with my hands just for you 💗",
                fontFamily = FontFamily.Cursive,
                fontSize = 32.sp,
                lineHeight = 36.sp,
                color = Color(0xFFD81B60),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(titleScale)
            )

            Spacer(modifier = Modifier.height(40.dp))

            LottieAnimation(
                composition = cardComposition,
                progress = { cardProgress },
                modifier = Modifier.size(320.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            AnimatedArrowButton(
                onClick = {
                    showPhotoDialog = true
                }
            )
        }

        LottieAnimation(
            composition = confettiComposition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.fillMaxSize()
        )

        if (showPhotoDialog) {

            PhotoMissionDialog(
                onTakePhoto = {
                    showPhotoDialog = false
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                },
                onDismiss = {
                    showPhotoDialog = false
                }
            )
        }
    }
}

fun saveBitmap(context: Context, bitmap: Bitmap): Boolean {

    return try {

        val file = File(
            context.cacheDir,
            "tanya_bday_photo_${System.currentTimeMillis()}.jpg"
        )

        val stream = FileOutputStream(file)

        bitmap.compress(
            Bitmap.CompressFormat.JPEG,
            95,
            stream
        )

        stream.flush()
        stream.close()

        true

    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}