package com.kc.android.rickandmorty.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kc.android.rickandmorty.data.entities.Location

@Dao
interface LocationDao {

    @Query("SELECT * FROM locations")
    fun getAllLocations(): LiveData<List<Location>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(locations: List<Location>)
}