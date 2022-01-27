package com.example.mymovie.core.domain.repository

import com.example.mymovie.core.data.Resource
import com.example.mymovie.core.data.source.remote.response.Cast
import kotlinx.coroutines.flow.Flow

interface IMovieDetailRepository {
    fun getCastMovie(movieId: Int, apiKey: String): Flow<Resource<List<Cast>>>
}