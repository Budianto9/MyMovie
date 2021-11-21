package com.example.mymovie.core.data.remote.datasource

import com.google.gson.annotations.SerializedName

data class BaseResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<Any?>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)
