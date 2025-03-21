package com.ahmetkaragunlu.favflixapp.repo

import com.ahmetkaragunlu.favflixapp.roomdb.FavFlixDao
import com.ahmetkaragunlu.favflixapp.roomdb.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavFlixRepo @Inject constructor(private val favFlixDao: FavFlixDao) {

    suspend fun add(item: Item) = favFlixDao.insert(item)
    suspend fun delete(item: Item) = favFlixDao.delete(item)
    suspend fun update(item: Item) = favFlixDao.update(item)
    fun getAllItems() : Flow<List<Item>> = favFlixDao.getAllItems().flowOn(Dispatchers.IO).distinctUntilChanged()
}