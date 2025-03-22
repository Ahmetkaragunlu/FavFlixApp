package com.ahmetkaragunlu.favflixapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ahmetkaragunlu.favflixapp.R
import com.ahmetkaragunlu.favflixapp.navigation.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(currentRoute : String, navController: NavController) {
    val title = when(currentRoute) {
        Screens.HOMESCREEN.route -> R.string.home_screen
        Screens.FAVORITESCREEN.route->R.string.favorite_screen
        Screens.DETAILSCREEN.route -> R.string.detail_screen
        Screens.ADDMOVIESCREEN.route -> R.string.add_movie_screen
        else -> R.string.home_screen
    }
    TopAppBar(
        title = { Text(text = stringResource(title)) },
        navigationIcon = {
            if (currentRoute != Screens.HOMESCREEN.route) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    )
}