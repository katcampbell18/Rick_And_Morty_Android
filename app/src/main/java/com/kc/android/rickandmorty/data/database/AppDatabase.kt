package com.kc.android.rickandmorty.data.database

import android.content.Context
import androidx.room.*
import com.kc.android.rickandmorty.data.entities.CharacterItem
import com.kc.android.rickandmorty.data.entities.Episode
import com.kc.android.rickandmorty.data.entities.Location
import com.kc.android.rickandmorty.utils.StringTypeConverters


@Database(entities = [CharacterItem::class, Location::class, Episode::class], version = 1, exportSchema = false)
@TypeConverters(StringTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
    abstract fun episodeDao(): EpisodeDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "characters")
                .fallbackToDestructiveMigration()
                .build()
    }
}