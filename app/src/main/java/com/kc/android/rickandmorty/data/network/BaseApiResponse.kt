package com.kc.android.rickandmorty.data.network

import com.kc.android.rickandmorty.utils.Resource
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> getResult(apiCall: suspend () -> Response<T>): Resource<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Resource.success(body)
                }
            }
                return error(
                    "${response.code()} ${response.message()}"
                )
            } catch (e: Exception) {
                return error(e.message ?: e.toString())
            }
        }

    private fun <T> error(errorMessage: String): Resource<T> {
        return Resource.error("Network call failed $errorMessage")
    }
}