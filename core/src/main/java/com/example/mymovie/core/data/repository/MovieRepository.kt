package com.example.mymovie.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mymovie.core.data.source.remote.datasource.MovieDataSource
import com.example.mymovie.core.data.source.remote.network.Api
import com.example.mymovie.core.data.source.remote.response.MovieResponse
import com.example.mymovie.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val apiService: Api
) : IMovieRepository {
    override fun getMoviePopularPage(apiKey: String): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                MovieDataSource(apiService, apiKey)
            }
        ).flow
    }
}