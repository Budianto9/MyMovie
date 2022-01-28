package com.example.mymovie.core.data.source.remote.network

import com.example.mymovie.core.data.source.remote.response.CreditsListResponse
import com.example.mymovie.core.data.source.remote.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("movie/now_playing")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ) : Response<MovieListResponse>


    @GET("movie/{movie_id}/credits")
    suspend fun getCreditsMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ) : Response<CreditsListResponse>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimiliarMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ) : Response<MovieListResponse>

}