package com.example.happybirthdaytanya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.happybirthdaytanya.ui.theme.HappyBirthdayTanyaTheme
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MusicPlayer.start(this)
        setContent {

            val context = LocalContext.current

            var showSplash by remember { mutableStateOf(true) }

            val isFirstOpen = AppState.isFirstOpen(context)

            var currentScreen by remember {
                mutableStateOf(Screen.values()[AppState.getLastScreen(context)])
            }

            HappyBirthdayTanyaTheme {

                if (showSplash) {

                    SplashScreen(
                        isFirstOpen = isFirstOpen,
                        onTap = {

                            if (isFirstOpen) {
                                AppState.setFirstOpenDone(context)
                            }

                            showSplash = false
                        }
                    )

                } else {

                    when (currentScreen) {

                        Screen.WORLD -> {
                            WorldOfTanyaMayank(
                                onNext = {
                                    currentScreen = Screen.GOOD_MORNING
                                    AppState.saveScreen(context, Screen.GOOD_MORNING.ordinal)
                                }
                            )
                        }

                        Screen.GOOD_MORNING -> {
                            GoodMorningScreen(
                                onNext = {
                                    currentScreen = Screen.WASHROOM
                                    AppState.saveScreen(context, Screen.WASHROOM.ordinal)
                                }
                            )
                        }

                        Screen.WASHROOM -> {
                            WashroomScreen(
                                onNext = {
                                    currentScreen = Screen.KITCHEN
                                    AppState.saveScreen(context = context, Screen.KITCHEN.ordinal)
                                }
                            )
                        }

                        Screen.KITCHEN -> {
                            KitchenScreen(
                                onNext = {
                                   currentScreen = Screen.BDAY_CARD
                                    AppState.saveScreen(context, Screen.BDAY_CARD.ordinal)
                                }
                            )
                        }

                        Screen.BDAY_CARD -> {
                            BirthdayCardScreen(
                                onNext = {
                                    currentScreen = Screen.CARD_LOTTIE_SCREEN
                                    AppState.saveScreen(context = context, Screen.CARD_LOTTIE_SCREEN.ordinal)
                                }
                            )
                        }

                        Screen.CARD_LOTTIE_SCREEN -> {
                            CardLottieScreen(
                                onNext = {
                                    currentScreen = Screen.PLUSH_TOY
                                    AppState.saveScreen(context, Screen.PLUSH_TOY.ordinal)
                                }
                            )
                        }

                        Screen.PLUSH_TOY -> {
                            PlushToyScreen(
                                onNext = {
                                    currentScreen = Screen.CAKE_SCREEN
                                    AppState.saveScreen(context, Screen.CAKE_SCREEN.ordinal)
                                }
                            )
                        }

                        Screen.CAKE_SCREEN -> {
                            CakeScreen (
                                onNext = {
                                    currentScreen = Screen.GET_READY_SCREEN
                                    AppState.saveScreen(context, Screen.GET_READY_SCREEN.ordinal)
                                }
                            )
                        }

                        Screen.GET_READY_SCREEN -> {
                            GetReadyScreen(
                                onNext = {
                                    currentScreen = Screen.WATCH_SCREEN
                                    AppState.saveScreen(context, Screen.WATCH_SCREEN.ordinal)
                                }
                            )
                        }

                        Screen.WATCH_SCREEN -> {
                            WatchScreen(
                                onNext = {
                                    currentScreen = Screen.WALLET_SCREEN
                                    AppState.saveScreen(context, Screen.WALLET_SCREEN.ordinal)
                                }
                            )
                        }

                        Screen.WALLET_SCREEN -> {
                            WalletScreen(
                                onNext = {
                                    currentScreen = Screen.NAILS_SCREEN
                                    AppState.saveScreen(context = context, Screen.NAILS_SCREEN.ordinal)
                                }
                            )
                        }

                        Screen.NAILS_SCREEN -> {
                            NailsScreen(
                                onNext = {
                                    currentScreen = Screen.THE_DAY_SCREEN
                                    AppState.saveScreen(context, Screen.THE_DAY_SCREEN.ordinal)
                                }
                            )
                        }

                        Screen.THE_DAY_SCREEN -> {
                            TheDayScreen(
                                onNext = {
                                    showSplash = true
                                    currentScreen = Screen.WORLD

                                    AppState.saveScreen(context, Screen.WORLD.ordinal)
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()

        MusicPlayer.mediaPlayer?.let {

            AppState.saveSong(
                this,
                MusicPlayer.currentIndex,
                it.currentPosition
            )
        }
    }
}
