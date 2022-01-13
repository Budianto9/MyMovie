package com.example.mymovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("results")
    val results: List<MovieResponse>
)
