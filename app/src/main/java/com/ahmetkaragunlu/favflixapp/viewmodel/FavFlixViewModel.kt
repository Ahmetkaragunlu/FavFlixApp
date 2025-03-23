package com.ahmetkaragunlu.favflixapp.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmetkaragunlu.favflixapp.navigation.Screens
import com.ahmetkaragunlu.favflixapp.repo.FavFlixRepo
import com.ahmetkaragunlu.favflixapp.roomdb.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavFlixViewModel @Inject constructor(private val repo: FavFlixRepo) : ViewModel() {

    private val _uiState = MutableStateFlow<List<Item>>(emptyList())
    val uiState: StateFlow<List<Item>> = _uiState.asStateFlow()
    private val _favoriteState = MutableStateFlow<List<Item>>(emptyList())
    val favoriteState : StateFlow<List<Item>> = _favoriteState.asStateFlow()

    init {
        viewModelScope.launch {
            repo.getAllItems().collect { itemList ->
                _uiState.value = itemList
            }
        }
        viewModelScope.launch {
            repo.getAllFavorites().collect { item ->
                _favoriteState.value=item
            }
        }
        }






    var inputTitle by mutableStateOf("")
        private set
    var inputCategory by mutableStateOf("")
        private set
    var inputRating by mutableStateOf("")
        private set
    var inputUserRating by mutableStateOf("")
        private set


    fun setTitle(title: String) {
        inputTitle = title

    }

    fun setCategory(category: String) {
        inputCategory = category

    }

    fun setRating(rating: String) {
        inputRating = rating

    }

    fun setUserRating(userRating: String) {
        inputUserRating = userRating
    }

    fun saveButton() {
        val newItem = Item(
            movieTitle = inputTitle,
            category = inputCategory,
            rating = inputRating.toFloatOrNull() ?: 0.0f,
            userRating = inputUserRating.toFloatOrNull() ?: 0.0f
        )
        add(newItem)
    }

    fun clearField() {
        inputTitle = ""
        inputCategory = ""
        inputRating = ""
        inputUserRating = ""
    }

    fun deleteItem(item : Item) {
        delete(item)
    }


    fun buttonControl(): Boolean = inputTitle.isNotBlank() && inputRating.isNotBlank()
            && inputCategory.isNotBlank() && inputUserRating.isNotBlank()

    fun add(item: Item) = viewModelScope.launch { repo.add(item) }
    fun delete(item: Item) = viewModelScope.launch { repo.delete(item) }
    fun update(item: Item) = viewModelScope.launch { repo.update(item) }
    fun updateFavoriteStatus(itemId : Int, isFavorite : Boolean)=viewModelScope.launch { repo.updateFavoritesStatus(itemId,isFavorite) }

}