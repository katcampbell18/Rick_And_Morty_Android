package com.kc.android.rickandmorty.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kc.android.rickandmorty.data.database.AppDatabase
import com.kc.android.rickandmorty.data.database.CharacterDao
import com.kc.android.rickandmorty.data.network.ApiService
import com.kc.android.rickandmorty.data.network.RemoteDataSource
import com.kc.android.rickandmorty.data.repository.CharacterRepository
import com.kc.android.rickandmorty.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService) = RemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource,
                          localDataSource: CharacterDao) =
        CharacterRepository(remoteDataSource, localDataSource)
}