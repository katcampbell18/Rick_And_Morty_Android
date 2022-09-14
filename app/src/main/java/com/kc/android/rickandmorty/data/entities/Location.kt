package com.kc.android.rickandmorty.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class Location(
    @PrimaryKey
    val location_id: Int,
    val location_name: String?,
    val location_url: String,
)
