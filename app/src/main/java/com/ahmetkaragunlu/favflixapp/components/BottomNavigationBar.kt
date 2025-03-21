package com.ahmetkaragunlu.favflixapp.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ahmetkaragunlu.favflixapp.model.items

@Composable
fun BottomNavigation(currentRoute : String,navController: NavController) {

    NavigationBar {
        items.forEach { item->
         NavigationBarItem(
         icon = { Icon(imageVector = item.icon, contentDescription = null) },
          label = { Text(text = stringResource(item.title)) },
           selected = currentRoute==item.route,
             onClick = {navController.navigate(item.route)}
         )
        }
    }
}