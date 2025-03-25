package com.ahmetkaragunlu.favflixapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmetkaragunlu.favflixapp.R
import com.ahmetkaragunlu.favflixapp.components.AppTextField
import com.ahmetkaragunlu.favflixapp.navigation.Screens


@Composable
fun AddMovieScreen(
    modifier: Modifier = Modifier,
    title: String,
    ontitleChange: (String) -> Unit,
    rating: String,
    onRatingChange: (String) -> Unit,
    category: String,
    onCategoryChange: (String) -> Unit,
    userRating: String,
    onUserRatingChange: (String) -> Unit,
    navController: NavController,
    saveButton: () -> Unit,
    buttonControl: () -> Boolean,
    clearField: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AppTextField(
            value = title,
            onValueChange = ontitleChange,
            label = R.string.title,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        AppTextField(
            value = category,
            onValueChange = onCategoryChange,
            label = R.string.category,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        AppTextField(
            value = rating,
            onValueChange = onRatingChange,
            label = R.string.rating,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        AppTextField(
            value = userRating,
            onValueChange = onUserRatingChange,
            label = R.string.user_rating,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )
        Button(
            onClick = {
                navController.navigate(Screens.HOMESCREEN.route)
                saveButton()
                clearField()
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = buttonControl(),
            shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 32.dp)
        ) {
            Text(
                text = stringResource(R.string.save),
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}