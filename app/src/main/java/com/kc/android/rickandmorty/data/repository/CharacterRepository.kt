package com.kc.android.rickandmorty.data.repository

import com.kc.android.rickandmorty.data.database.CharacterDao
import com.kc.android.rickandmorty.data.database.EpisodeDao
import com.kc.android.rickandmorty.data.database.LocationDao
import com.kc.android.rickandmorty.data.network.RemoteDataSource
import com.kc.android.rickandmorty.utils.performGetOperations
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: CharacterDao,
    private val localDataSource2: LocationDao,
    private val localDataSource3: EpisodeDao

) {
    fun getCharacter(id: Int) = performGetOperations(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.getCharacter(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getCharacters() = performGetOperations(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getCharacters() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )
}