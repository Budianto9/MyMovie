package com.example.mymovie.core.domain.usecase

import com.example.mymovie.core.data.Resource
import com.example.mymovie.core.data.source.remote.response.Cast
import com.example.mymovie.core.data.source.remote.response.MovieResponse
import com.example.mymovie.core.domain.repository.IMovieDetailRepository
import kotlinx.coroutines.flow.Flow

class MovieDetailInteractor(private val movieDetailRepository: IMovieDetailRepository): MovieDetailUseCase {
    override fun getMovieCast(movieId: Int, apiKey: String): Flow<Resource<List<Cast>>> =
        movieDetailRepository.getCastMovie(movieId, apiKey)

    override fun getSimiliarMovie(
        movieId: Int,
        apiKey: String
    ): Flow<Resource<List<MovieResponse>>> =
        movieDetailRepository.getSimiliarMovie(movieId, apiKey)
}