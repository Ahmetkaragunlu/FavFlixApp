package com.ahmetkaragunlu.favflixapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmetkaragunlu.favflixapp.components.AppTopBar
import com.ahmetkaragunlu.favflixapp.components.BottomNavigation
import com.ahmetkaragunlu.favflixapp.screens.AddMovieScreen
import com.ahmetkaragunlu.favflixapp.screens.DetailScreen
import com.ahmetkaragunlu.favflixapp.screens.EditMovieSceen
import com.ahmetkaragunlu.favflixapp.screens.FavoriteScreen
import com.ahmetkaragunlu.favflixapp.screens.HomeScreen
import com.ahmetkaragunlu.favflixapp.viewmodel.FavFlixViewModel


@Composable
fun FavFlixNavigation(
    modifier: Modifier = Modifier,
    viewModel: FavFlixViewModel = hiltViewModel()
) {

    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val favoriteState by viewModel.favoriteState.collectAsStateWithLifecycle()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: Screens.HOMESCREEN.route


    Scaffold(
        topBar = {
            AppTopBar(
                currentRoute = currentRoute,
                navController = navController,
                clearField = { viewModel.clearField() })
        },
        bottomBar = { BottomNavigation(currentRoute = currentRoute, navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController, startDestination = Screens.HOMESCREEN.route,
            modifier = modifier.padding(paddingValues)
        ) {
            composable(
                route = Screens.HOMESCREEN.route
            ) {
                HomeScreen(
                    navController = navController,
                    movieList = uiState,
                    isFavoriteStatus = { itemId, isFavorite ->
                        viewModel.updateFavoriteStatus(
                            itemId,
                            isFavorite
                        )
                    }
                )
            }
            composable(
                route = "DetailScreen/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                val itemId = navBackStackEntry.arguments?.getInt("itemId")
                DetailScreen(
                    itemId = itemId,
                    itemList = uiState,
                    deleteItem = { viewModel.deleteItem(it) },
                    navController = navController
                )
            }
            composable(route = Screens.FAVORITESCREEN.route) {
                FavoriteScreen(
                    favoriteList = favoriteState,
                    isFavoriteStatus = {itemId,isFavorite -> viewModel.updateFavoriteStatus(itemId,isFavorite)}
                )
            }
            composable(
                route = "EditMovieScreen/{itemId}",
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                val itemId = navBackStackEntry.arguments?.getInt("itemId")
                EditMovieSceen(
                    itemId = itemId,
                    movieList = uiState,
                    updateItem = { viewModel.update(it) },
                    navController = navController
                )
            }
            composable(route = Screens.ADDMOVIESCREEN.route) {
                AddMovieScreen(
                    title = viewModel.inputTitle,
                    ontitleChange = { viewModel.setTitle(it) },
                    category = viewModel.inputCategory,
                    onCategoryChange = { viewModel.setCategory(it) },
                    rating = viewModel.inputRating,
                    onRatingChange = { viewModel.setRating(it) },
                    userRating = viewModel.inputUserRating,
                    onUserRatingChange = { viewModel.setUserRating(it) },
                    navController = navController,
                    saveButton = { viewModel.saveButton() },
                    buttonControl = { viewModel.buttonControl() },
                    clearField = { viewModel.clearField() }
                )
            }
        }
    }
}