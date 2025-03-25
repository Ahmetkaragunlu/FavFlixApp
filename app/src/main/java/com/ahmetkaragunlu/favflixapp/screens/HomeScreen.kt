package com.ahmetkaragunlu.favflixapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetkaragunlu.favflixapp.R
import com.ahmetkaragunlu.favflixapp.navigation.Screens
import com.ahmetkaragunlu.favflixapp.roomdb.Item

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    movieList: List<Item>,
    isFavoriteStatus: (Int, Boolean) -> Unit,
    searchResult: List<Item>,
    searchMoviesByTitleOrCategory: (String) -> Unit,
    query: String
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = modifier.fillMaxWidth()) {
            SearchBar(
                query = query,
                onQueryChanged = { searchMoviesByTitleOrCategory(it) }
            )
            val displayList = if (query.isEmpty()) movieList else searchResult
            LazyColumn {
                items(displayList) { item ->
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                        onClick = {
                            navController.navigate("${Screens.DETAILSCREEN.route}/${item.id}")
                        }
                    ) {
                        Column(
                            modifier = modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(
                                modifier = modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.movie_title, item.movieTitle),
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = modifier.weight(1f)
                                )
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = null,
                                    modifier = modifier.clickable {
                                        isFavoriteStatus(item.id, !item.isFavorite)
                                    },
                                    tint = if (item.isFavorite) Color.Yellow else Color.White
                                )
                            }
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
        }

        FloatingActionButton(
            onClick = { navController.navigate(Screens.ADDMOVIESCREEN.route) },
            modifier = modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }
}
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        placeholder = { Text(text = stringResource(R.string.search_film)) }
    )
}