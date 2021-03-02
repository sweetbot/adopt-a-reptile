package com.example.androiddevchallenge

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.androiddevchallenge.utils.LocalBackDispatcher
import com.example.androiddevchallenge.utils.ProvideImageLoader
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun AnimalApp(backDispatcher: OnBackPressedDispatcher) {

    CompositionLocalProvider(LocalBackDispatcher provides backDispatcher) {
        ProvideWindowInsets {
            ProvideImageLoader {
                NavGraph()
            }
        }
    }
}
