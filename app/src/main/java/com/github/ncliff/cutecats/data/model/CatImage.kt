package com.github.ncliff.cutecats.data.model

import com.google.gson.annotations.SerializedName

data class CatImage(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("breeds")
	val breeds: List<CatBreeds>? = null,

	@field:SerializedName("categories")
	val categories: List<CategoriesItem?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class CategoriesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
