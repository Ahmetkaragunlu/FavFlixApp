package com.ahmetkaragunlu.favflixapp.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface FavFlixDao {

    @Query("Select * From items ORDER by rating DESC")
    fun getAllItems():Flow<List<Item>>

    @Query("Select * From items Where isFavorite = 1")
    fun getAllFavorites () : Flow<List<Item>>

    @Query("SELECT * FROM items WHERE title LIKE '%' || :query || '%' OR category LIKE '%' || :query || '%'")
    fun searchMoviesByTitleOrCategory(query: String): Flow<List<Item>>

    @Query("UPDATE items SET isFavorite = :isFavorite WHERE id = :itemId")
    suspend fun updateFavoriteStatus(itemId: Int, isFavorite: Boolean)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Update
    suspend fun update(item: Item)

}