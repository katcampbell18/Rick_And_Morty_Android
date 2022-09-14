package com.kc.android.rickandmorty.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
data class Episode(
    @PrimaryKey
    val id: Int,
    val episode_name: String,
    val air_date: String,
    val episode_url: String,
)
