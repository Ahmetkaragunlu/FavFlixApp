package com.ahmetkaragunlu.favflixapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetkaragunlu.favflixapp.navigation.Screens
import com.ahmetkaragunlu.favflixapp.roomdb.Item

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    movieList: List<Item>
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(movieList) { item->
                Text(
                    text = item.movieTitle,
                    style =MaterialTheme.typography.bodyLarge
                    )
                Text(
                    text = item.category,
                    style =MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "${item.rating}",
                    style =MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "${item.userRating}",
                    style =MaterialTheme.typography.bodyLarge
                )
            }
        }
        FloatingActionButton(
            onClick = {navController.navigate(Screens.DETAILSCREEN.route)},
            modifier = modifier.padding(16.dp).align(Alignment.BottomEnd)
        ) {
         Icon(
             imageVector = Icons.Default.Add,
             contentDescription = null
         )
        }
    }
}
