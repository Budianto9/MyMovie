package com.example.mymovie.core.domain.repository

import androidx.paging.PagingData
import com.example.mymovie.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMoviePopularPage(apiKey: String) : Flow<PagingData<MovieResponse>>
}