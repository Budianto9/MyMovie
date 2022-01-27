package com.example.mymovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreditsListResponse(

	@field:SerializedName("cast")
	val cast: List<Cast>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("crew")
	val crew: List<Any?>? = null
)
