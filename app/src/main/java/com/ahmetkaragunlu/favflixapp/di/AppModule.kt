package com.ahmetkaragunlu.favflixapp.di

import android.content.Context
import androidx.room.Room
import com.ahmetkaragunlu.favflixapp.roomdb.FavFlixDao
import com.ahmetkaragunlu.favflixapp.roomdb.FaxFlixDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
  fun providesDao(providesDatabase: FaxFlixDatabase):FavFlixDao=providesDatabase.favFlixDao()

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context : Context) : FaxFlixDatabase =
        Room.databaseBuilder(
            context = context,
            FaxFlixDatabase::class.java,
           name = "items"
        ).fallbackToDestructiveMigration().build()
}