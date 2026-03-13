package com.example.happybirthdaytanya

import android.content.Context

object AppState {

    private const val PREF = "tanya_app"
    private const val FIRST_OPEN = "first_open"
    private const val LAST_SCREEN = "last_screen"
    private const val SONG_INDEX = "song_index"
    private const val SONG_POSITION = "song_position"

    fun isFirstOpen(context: Context): Boolean {
        val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return pref.getBoolean(FIRST_OPEN, true)
    }

    fun setFirstOpenDone(context: Context) {
        val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        pref.edit().putBoolean(FIRST_OPEN, false).apply()
    }

    fun saveScreen(context: Context, screen: Int) {
        val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        pref.edit().putInt(LAST_SCREEN, screen).apply()
    }

    fun getLastScreen(context: Context): Int {
        val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return pref.getInt(LAST_SCREEN, 0)
    }

    fun saveSong(context: Context, index: Int, position: Int) {
        val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        pref.edit()
            .putInt(SONG_INDEX, index)
            .putInt(SONG_POSITION, position)
            .apply()
    }

    fun getSongIndex(context: Context): Int {
        val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return pref.getInt(SONG_INDEX, 0)
    }

    fun getSongPosition(context: Context): Int {
        val pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return pref.getInt(SONG_POSITION, 0)
    }

    fun resetApp(context: Context) {

        val prefs = context.getSharedPreferences("app_state", Context.MODE_PRIVATE)

        prefs.edit()
            .putBoolean("first_open", true)
            .putInt("last_screen", 0)
            .apply()
    }
}