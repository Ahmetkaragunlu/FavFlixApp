package com.ahmetkaragunlu.favflixapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Delete
import com.ahmetkaragunlu.favflixapp.R
import com.ahmetkaragunlu.favflixapp.navigation.Screens
import com.ahmetkaragunlu.favflixapp.roomdb.Item


@Composable
fun DetailScreen(
    itemId: Int?,
    modifier: Modifier = Modifier,
    itemList: List<Item>,
    deleteItem : (Item) -> Unit,
    navController: NavController
) {
    val selectedItem = itemList.firstOrNull { item -> item.id == itemId }

    if (selectedItem != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    modifier = modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.movie_title, selectedItem.movieTitle),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = stringResource(R.string.movie_category, selectedItem.category),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = stringResource(R.string.movie_rating, selectedItem.rating),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = stringResource(R.string.movie_user_rating, selectedItem.userRating),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
                Button(
                    onClick = {
                        deleteItem(selectedItem)
                    },
                    modifier = modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
                    shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 32.dp)
                ) {
                    Text(text = stringResource(R.string.delete))
                }
            Button(
                onClick = {
                    navController.navigate("${Screens.EDITMOVIESCREEN.route}/${selectedItem.id}")
                          },
                modifier = modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 32.dp)
            ) {
                Text(text = stringResource(R.string.update_rating))
            }
        }

    }
}