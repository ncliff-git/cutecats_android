package com.github.ncliff.cutecats.data.network

import com.google.gson.annotations.SerializedName

data class CatVote(

	@field:SerializedName("sub_id")
	val subId: String? = null,

	@field:SerializedName("image_id")
	val imageId: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)
