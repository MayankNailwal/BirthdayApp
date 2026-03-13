package com.example.happybirthdaytanya

import android.content.Context
import android.media.MediaPlayer

object MusicPlayer {

    var mediaPlayer: MediaPlayer? = null
    var currentIndex = 0

    data class Song(
        val resId: Int,
        val startMs: Int
    )

    private val playlist = listOf(
        Song(R.raw.dekha_tenu, 11000),
        Song(R.raw.jogi, 13000),
        Song(R.raw.tu_hi_has_de,40000),
        Song(R.raw.bareeshein, 10000),
        Song(R.raw.tu_hai_toh, 10000),
    )

    fun start(context: Context) {

        if (mediaPlayer != null) return

        currentIndex = AppState.getSongIndex(context)
        playSong(context, AppState.getSongPosition(context))
    }

    private fun playSong(context: Context, startPosition: Int = 0) {

        val song = playlist[currentIndex]

        mediaPlayer?.release()

        mediaPlayer = MediaPlayer.create(context, song.resId)

        mediaPlayer?.setOnPreparedListener {

            val position = if (startPosition > 0) startPosition else song.startMs
            it.seekTo(position)

            it.start()
        }

        mediaPlayer?.setOnCompletionListener {

            currentIndex++

            if (currentIndex >= playlist.size) {
                currentIndex = 0
            }

            playSong(context)
        }
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}