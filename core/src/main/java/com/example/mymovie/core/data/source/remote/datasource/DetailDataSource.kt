package com.example.mymovie.core.data.source.remote.datasource

import com.example.mymovie.core.data.source.remote.network.Api
import com.example.mymovie.core.data.source.remote.network.ApiResponse
import com.example.mymovie.core.data.source.remote.response.Cast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DetailDataSource(private val api: Api){

    suspend fun getCastMovie(movieId: Int, apiKey: String): Flow<ApiResponse<List<Cast>>> =
        flow {
            try {
                val response = api.getCreditsMovie(movieId, apiKey)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        if (body.cast!!.isNotEmpty()) emit(ApiResponse.Success(body.cast)) else emit(ApiResponse.Empty)
                    } else emit(ApiResponse.Error("Terjadi Kesalahan"))
                } else emit(ApiResponse.Error("Terjadi Kesalahan"))
            } catch (e: Exception) {
                emit(ApiResponse.Error("Terjadi Kesalahan"))
            }
        }.flowOn(Dispatchers.IO)
}