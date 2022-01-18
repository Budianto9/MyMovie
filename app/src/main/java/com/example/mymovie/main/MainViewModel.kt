package com.example.mymovie.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mymovie.BuildConfig
import com.example.mymovie.core.domain.usecase.MovieUseCase

class MainViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    private val apiKey = BuildConfig.API_KEY

    val movie = movieUseCase.getMoviePopularPage(apiKey).asLiveData()
}