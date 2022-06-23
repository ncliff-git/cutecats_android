package com.github.ncliff.cutecats.model

import com.google.gson.annotations.SerializedName

data class CatResponses(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
)
