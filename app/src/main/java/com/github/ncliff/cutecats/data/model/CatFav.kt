package com.github.ncliff.cutecats.data.model

import com.google.gson.annotations.SerializedName

data class CatFav(

	@field:SerializedName("image")
	val image: Image? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("sub_id")
	val subId: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image_id")
	val imageId: String? = null
)
