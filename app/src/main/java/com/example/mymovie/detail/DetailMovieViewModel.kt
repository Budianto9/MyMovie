package com.example.mymovie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovie.BuildConfig
import com.example.mymovie.core.data.Resource
import com.example.mymovie.core.data.source.remote.response.Cast
import com.example.mymovie.core.domain.usecase.MovieDetailUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val movieDetailUseCase: MovieDetailUseCase) : ViewModel(){
    private val apiKey = BuildConfig.ApiKey

    private val _cast = MutableStateFlow<Resource<List<Cast>>>(Resource.Loading())
    val cast: Flow<Resource<List<Cast>>>
        get() = _cast

    fun getMovieCast(movieId: Int){
        viewModelScope.launch {
            _cast.value = Resource.Loading()
            movieDetailUseCase.getMovieCast(movieId, apiKey).collect{
                _cast.value = it
            }
        }
    }
}