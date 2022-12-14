package com.kc.android.rickandmorty.data.network

import com.kc.android.rickandmorty.data.entities.CharacterItem
import com.kc.android.rickandmorty.data.entities.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterList>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<CharacterItem>

}