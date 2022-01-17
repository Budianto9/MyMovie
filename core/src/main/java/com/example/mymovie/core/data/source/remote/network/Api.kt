package com.example.mymovie.core.data.source.remote.network

import com.example.mymovie.core.data.source.remote.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("3/discover/movie?sort_by=vote_count.desc")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ) : Response<MovieListResponse>

}