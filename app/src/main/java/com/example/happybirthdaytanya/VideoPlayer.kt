package com.example.happybirthdaytanya

import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
    onVideoFinished: () -> Unit
) {

    val context = LocalContext.current

    val exoPlayer = remember {

        val uri =
            "android.resource://${context.packageName}/${R.raw.bday_video}"

        ExoPlayer.Builder(context).build().apply {

            val mediaItem = MediaItem.fromUri(uri)

            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true

            addListener(object : Player.Listener {

                override fun onPlaybackStateChanged(state: Int) {

                    if (state == Player.STATE_ENDED) {
                        onVideoFinished()
                    }
                }
            })
        }
    }

    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = {
            PlayerView(it).apply {

                player = exoPlayer
                useController = false

                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
            }
        }
    )
}