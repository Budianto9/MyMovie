package com.example.mymovie.core.data.repository

import com.example.mymovie.core.data.OnlyNetworkBoundResource
import com.example.mymovie.core.data.Resource
import com.example.mymovie.core.data.source.remote.datasource.DetailDataSource
import com.example.mymovie.core.data.source.remote.network.ApiResponse
import com.example.mymovie.core.data.source.remote.response.Cast
import com.example.mymovie.core.data.source.remote.response.MovieResponse
import com.example.mymovie.core.domain.repository.IMovieDetailRepository
import kotlinx.coroutines.flow.Flow

class MovieDetailRepository(
    private val movieDetilDataSource: DetailDataSource
) : IMovieDetailRepository{
    override fun getCastMovie(movieId: Int, apiKey: String): Flow<Resource<List<Cast>>> =
        object : OnlyNetworkBoundResource<List<Cast>>(){
            override suspend fun createCall(): Flow<ApiResponse<List<Cast>>> {
                return movieDetilDataSource.getCastMovie(movieId, apiKey)
            }
        }.asFlow()

    override fun getSimiliarMovie(
        movieId: Int,
        apiKey: String
    ): Flow<Resource<List<MovieResponse>>> =
        object : OnlyNetworkBoundResource<List<MovieResponse>>(){
            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return movieDetilDataSource.getSimiliarMovie(movieId, apiKey)
            }
        }.asFlow()
}