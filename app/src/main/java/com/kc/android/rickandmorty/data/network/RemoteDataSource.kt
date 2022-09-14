package com.kc.android.rickandmorty.data.network

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
): BaseApiResponse() {

    suspend fun getCharacters() = getResult { apiService.getAllCharacters() }
    suspend fun getCharacter(id: Int) = getResult { apiService.getCharacter(id) }
}