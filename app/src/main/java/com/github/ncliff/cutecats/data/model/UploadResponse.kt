package com.github.ncliff.cutecats.data.model

import com.google.gson.annotations.SerializedName

data class UploadResponse(

	@field:SerializedName("approved")
	val approved: Int? = null,

	@field:SerializedName("original_filename")
	val originalFilename: String? = null,

	@field:SerializedName("level")
	val level: String? = null,

	@field:SerializedName("pending")
	val pending: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
