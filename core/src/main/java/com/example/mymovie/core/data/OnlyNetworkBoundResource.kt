package com.example.mymovie.core.data

import com.example.mymovie.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class OnlyNetworkBoundResource<ResultType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading<ResultType>())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(apiResponse.data))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Empty<ResultType>())
            }
            is ApiResponse.Error -> {
                emit(Resource.Error<ResultType>(apiResponse.errorMessage))
            }
        }
    }

    protected abstract suspend fun createCall(): Flow<ApiResponse<ResultType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}