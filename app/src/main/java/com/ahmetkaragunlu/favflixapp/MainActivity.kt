package com.ahmetkaragunlu.favflixapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ahmetkaragunlu.favflixapp.navigation.FavFlixNavigation
import com.ahmetkaragunlu.favflixapp.ui.theme.FavFlixAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FavFlixAppTheme {
                FavFlixNavigation()
            }
        }
    }
}

