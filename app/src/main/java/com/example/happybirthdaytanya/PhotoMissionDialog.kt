package com.example.happybirthdaytanya

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PhotoMissionDialog(
    onTakePhoto: () -> Unit,
    onDismiss: () -> Unit
) {

    AlertDialog(
        onDismissRequest = onDismiss,

        confirmButton = {
            Button(onClick = onTakePhoto) {
                Text("Take Photo 📸")
            }
        },

        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Not Yet")
            }
        },

        title = {
            Text("One More Thing 🥹")
        },

        text = {
            Text(
                "Take a photo with me and the birthday card 💗\n\n" +
                        "That photo will unlock the next surprise!"
            )
        }
    )
}