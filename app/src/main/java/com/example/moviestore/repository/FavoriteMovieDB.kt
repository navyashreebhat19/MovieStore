package com.example.moviestore

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteMovie::class], version = 2)
abstract class FavoriteMovieDB : RoomDatabase(){
    abstract fun favoriteMovieDao() : FavoriteMovieDao

    companion object{
        var INSTANCE: FavoriteMovieDB? = null

        fun getDB(context: Context): FavoriteMovieDB{
            if(INSTANCE == null){
                synchronized(FavoriteMovieDB::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteMovieDB::class.java,
                        "myDbB"
                    )
                        .fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE!!
        }

        fun destroyDB(){
            INSTANCE = null
        }
    }
}