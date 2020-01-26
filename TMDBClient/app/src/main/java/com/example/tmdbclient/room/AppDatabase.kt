package com.example.tmdbclient.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdbclient.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getPopularMoviesDao():PopularMoviesDao
}