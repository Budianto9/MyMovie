package com.example.mymovie.core.domain.usecase

import androidx.paging.PagingData
import com.example.mymovie.core.data.source.remote.response.MovieResponse
import com.example.mymovie.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(
    private val movieRepository: IMovieRepository
): MovieUseCase {

    override fun getMoviePopularPage(apiKey: String): Flow<PagingData<MovieResponse>> =
        movieRepository.getMoviePopularPage(apiKey)
}