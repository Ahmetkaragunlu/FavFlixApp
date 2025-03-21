package com.ahmetkaragunlu.favflixapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmetkaragunlu.favflixapp.components.BottomNavigation
import com.ahmetkaragunlu.favflixapp.components.EditTopBar
import com.ahmetkaragunlu.favflixapp.screens.HomeScreen
import com.ahmetkaragunlu.favflixapp.viewmodel.FavFlixViewModel


@Composable
fun FavFlixNavigation(modifier: Modifier = Modifier,viewModel:FavFlixViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: Screens.HOMESCREEN.route


    Scaffold(
        topBar = { EditTopBar(currentRoute = currentRoute, navController = navController) },
        bottomBar = { BottomNavigation(currentRoute = currentRoute, navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController, startDestination = Screens.HOMESCREEN.route,
            modifier = modifier.padding(paddingValues)
        ) {
            composable(route = Screens.HOMESCREEN.route) {
             HomeScreen(
                 navController = navController,
                 movieList = uiState
             )
            }
            composable(route = Screens.FAVORITESCREEN.route) {
            }
            composable(route = Screens.DETAILSCREEN.route) {

            }
        }
    }
}