package com.example.mymovie.core.di

import com.example.mymovie.core.data.repository.MovieDetailRepository
import com.example.mymovie.core.data.repository.MovieRepository
import com.example.mymovie.core.data.source.remote.datasource.DetailDataSource
import com.example.mymovie.core.data.source.remote.network.Api
import com.example.mymovie.core.domain.repository.IMovieDetailRepository
import com.example.mymovie.core.domain.repository.IMovieRepository
import com.example.mymovie.core.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(Api::class.java)
    }
}

val repositoryModule = module {
    single<IMovieRepository> { MovieRepository(get()) }
    single { DetailDataSource(get()) }
    single<IMovieDetailRepository> { MovieDetailRepository(get()) }
}