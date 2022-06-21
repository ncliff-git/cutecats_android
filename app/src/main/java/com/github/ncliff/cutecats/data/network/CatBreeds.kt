package com.github.ncliff.cutecats.data.network

import com.google.gson.annotations.SerializedName

data class CatBreeds(

	@field:SerializedName("wikipedia_url")
	val wikipediaUrl: String? = null,

	@field:SerializedName("origin")
	val origin: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("life_span")
	val lifeSpan: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("image")
	val image: Image? = null,

	@field:SerializedName("weight")
	val weight: Weight? = null,

	@field:SerializedName("temperament")
	val temperament: String? = null,

	@field:SerializedName("name")
	val name: String? = null,
)

data class Weight(

	@field:SerializedName("metric")
	val metric: String? = null,

)

data class Image(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)
