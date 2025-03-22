package com.ahmetkaragunlu.favflixapp.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ahmetkaragunlu.favflixapp.model.items

@Composable
fun BottomNavigation(currentRoute : String,navController: NavController) {

    NavigationBar(containerColor = MaterialTheme.colorScheme.secondaryContainer) {
        items.forEach { item->
         NavigationBarItem(
         icon = { Icon(imageVector = item.icon, contentDescription = null) },
          label = { Text(text = stringResource(item.title)) },
           selected = currentRoute==item.route,
             onClick = {navController.navigate(item.route)},
             colors = NavigationBarItemDefaults.colors(
                 indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                 selectedTextColor =MaterialTheme.colorScheme.primary,
                 selectedIconColor = MaterialTheme.colorScheme.primary
             ),

         )
        }
    }
}