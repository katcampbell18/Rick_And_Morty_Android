package com.kc.android.rickandmorty.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kc.android.rickandmorty.data.entities.CharacterItem
import com.kc.android.rickandmorty.data.entities.Episode
import com.kc.android.rickandmorty.data.entities.Location

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): LiveData<List<CharacterItem>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: Int): LiveData<CharacterItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterItem)
}