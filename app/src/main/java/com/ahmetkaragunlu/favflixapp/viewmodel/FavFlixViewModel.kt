package com.ahmetkaragunlu.favflixapp.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val uiState : StateFlow<List<Item>> =_uiState.asStateFlow()

    init {
        viewModelScope.launch {
        repo.getAllItems().collect { itemList ->
        _uiState.value = itemList
          }
        }
    }


    fun add(item: Item) = viewModelScope.launch { repo.add(item)}
    fun delete(item: Item) = viewModelScope.launch { repo.delete(item)}
    fun update(item: Item) = viewModelScope.launch { repo.update(item) }
}