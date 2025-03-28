package com.ahmetkaragunlu.favflixapp.repo

import com.ahmetkaragunlu.favflixapp.roomdb.FavFlixDao
import com.ahmetkaragunlu.favflixapp.roomdb.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class FavFlixRepo @Inject constructor(private val favFlixDao: FavFlixDao) {

    suspend fun add(item: Item) = favFlixDao.insert(item)
    suspend fun delete(item: Item) = favFlixDao.delete(item)
    suspend fun update(item: Item) = favFlixDao.update(item)
    fun getAllItems(): Flow<List<Item>> = favFlixDao.getAllItems().distinctUntilChanged()
    fun getAllFavorites(): Flow<List<Item>> = favFlixDao.getAllFavorites().distinctUntilChanged()
    fun searchMoviesByTitleOrCategory(query: String): Flow<List<Item>> = favFlixDao.searchMoviesByTitleOrCategory(query).distinctUntilChanged()
    suspend fun updateFavoritesStatus(itemId: Int, isFavorite: Boolean) =
        favFlixDao.updateFavoriteStatus(itemId, isFavorite)
}