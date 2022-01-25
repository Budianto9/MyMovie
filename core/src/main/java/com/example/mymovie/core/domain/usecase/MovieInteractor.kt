package com.example.mymovie.core.domain.usecase

import com.example.mymovie.core.domain.repository.IMovieRepository

class MovieInteractor(
    private val movieRepository: IMovieRepository
): MovieUseCase {

    override fun getMoviePopularPage(apiKey: String) =
        movieRepository.getMoviePopularPage(apiKey)
}