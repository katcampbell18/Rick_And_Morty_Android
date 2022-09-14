package com.kc.android.rickandmorty.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterItem(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @Embedded
    val location: Location?,
    val image: String,
    val episode: Episode?,
    val url: String,
    val created: String,
)
