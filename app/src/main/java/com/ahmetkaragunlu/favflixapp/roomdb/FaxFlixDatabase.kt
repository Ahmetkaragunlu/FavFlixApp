package com.ahmetkaragunlu.favflixapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 2, exportSchema = false)
abstract class FaxFlixDatabase : RoomDatabase() {
 abstract fun favFlixDao () : FavFlixDao
}