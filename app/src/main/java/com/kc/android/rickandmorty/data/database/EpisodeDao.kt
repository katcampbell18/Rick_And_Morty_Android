package com.kc.android.rickandmorty.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kc.android.rickandmorty.data.entities.Episode

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM episodes")
    fun getAllEpisodes(): LiveData<List<Episode>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(episodes: List<Episode>)
}