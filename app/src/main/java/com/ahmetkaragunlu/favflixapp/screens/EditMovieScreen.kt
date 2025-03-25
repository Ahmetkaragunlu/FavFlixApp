package com.ahmetkaragunlu.favflixapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetkaragunlu.favflixapp.R
import com.ahmetkaragunlu.favflixapp.components.AppTextField
import com.ahmetkaragunlu.favflixapp.navigation.Screens
import com.ahmetkaragunlu.favflixapp.roomdb.Item

@Composable
fun EditMovieSceen(
    modifier: Modifier = Modifier,
    itemId: Int?,
    movieList: List<Item>,
    updateItem: (Item) -> Unit,
    navController: NavController
) {
    val selectedId = movieList.firstOrNull { id -> id.id == itemId }
    var userRating by rememberSaveable { mutableStateOf(selectedId?.userRating?.toString() ?: " ") }
    if (selectedId != null) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AppTextField(
                value = userRating,
                onValueChange = { userRating = it },
                label = R.string.user_rating,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                )
            )
            Button(
                onClick = {
                    updateItem(selectedId.copy(userRating = userRating.toFloatOrNull() ?: 0f))
                    navController.navigate(Screens.HOMESCREEN.route)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 32.dp)
            ) {
                Text(text = stringResource(R.string.save))
            }
        }
    }
}