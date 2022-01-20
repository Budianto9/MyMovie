package com.example.mymovie.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mymovie.BuildConfig
import com.example.mymovie.core.data.source.remote.response.MovieResponse
import com.example.mymovie.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val apiKey = BuildConfig.API_KEY

    fun getMoviePopular() : Flow<PagingData<MovieResponse>> =
        movieUseCase.getMoviePopularPage(apiKey).cachedIn(viewModelScope)
}