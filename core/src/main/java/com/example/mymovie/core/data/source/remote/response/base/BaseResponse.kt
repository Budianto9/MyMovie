package com.example.mymovie.core.data.source.remote.response.base

import com.google.gson.annotations.SerializedName

open class BaseResponse<Item>(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<Item>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)
