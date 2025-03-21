package com.ahmetkaragunlu.favflixapp.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.ahmetkaragunlu.favflixapp.R
import com.ahmetkaragunlu.favflixapp.navigation.Screens

data class ScreenItem(
    @StringRes val title : Int,
    val icon : ImageVector,
    val route : String
)

val items= listOf(
    ScreenItem(
        title = R.string.home,
        icon = Icons.Default.Home,
        route = Screens.HOMESCREEN.route
    ),
    ScreenItem(
        title = R.string.favorites,
        icon = Icons.Default.Favorite,
        route = Screens.FAVORITESCREEN.route
    )
)
