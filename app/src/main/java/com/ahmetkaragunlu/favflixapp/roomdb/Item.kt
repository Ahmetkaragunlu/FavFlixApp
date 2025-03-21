package com.ahmetkaragunlu.favflixapp.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    @ColumnInfo(name = "title")
    val movieTitle : String = "",

    @ColumnInfo(name ="category")
    val category: String = "",

    @ColumnInfo(name = "rating")
    val rating : Float ,

   @ColumnInfo(name = "user_rating")
    val userRating : Float,


)
