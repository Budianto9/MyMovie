package com.example.mymovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @field:SerializedName("total_pages")
    val total_pages: Int,

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_results")
    val total_results: Int,

    @field:SerializedName("results")
    val results: List<MovieResponse>
)
