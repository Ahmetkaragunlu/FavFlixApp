package com.ahmetkaragunlu.favflixapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetkaragunlu.favflixapp.R
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
                items(movieList) { item ->
                    Card(
                        modifier = modifier.fillMaxWidth().padding(12.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                        onClick = {
                            navController.navigate("${Screens.DETAILSCREEN.route}/${item.id}")
                        }
                    ) {
                        Column(
                            modifier = modifier.fillMaxSize().padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.movie_title, item.movieTitle),
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = stringResource(R.string.movie_category, item.category),
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = stringResource(R.string.movie_rating, item.rating),
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = stringResource(R.string.movie_user_rating, item.userRating),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }

                }
            }
            FloatingActionButton(
                onClick = { navController.navigate(Screens.ADDMOVIESCREEN.route) },
                modifier = modifier.padding(16.dp).align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }

}
