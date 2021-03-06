package com.example.mymovie.di

import com.example.mymovie.core.domain.usecase.MovieDetailInteractor
import com.example.mymovie.core.domain.usecase.MovieDetailUseCase
import com.example.mymovie.core.domain.usecase.MovieInteractor
import com.example.mymovie.core.domain.usecase.MovieUseCase
import com.example.mymovie.detail.DetailMovieViewModel
import com.example.mymovie.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<MovieDetailUseCase> { MovieDetailInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}