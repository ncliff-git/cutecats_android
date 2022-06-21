package com.github.ncliff.cutecats.data.network

import retrofit2.Call
import retrofit2.http.*

interface CatApiService {
    @Headers("x-api-key: " + CatApiPref.CAT_API_KEY)
    @GET("breeds")
    fun getCatBreedsList(): Call<List<CatBreeds>>

    @Headers("x-api-key: " + CatApiPref.CAT_API_KEY)
    @GET("breeds/search")
    fun getCatBreedsByName(@Query("q")name: String): Call<List<CatBreeds>>

    @Headers("x-api-key: " + CatApiPref.CAT_API_KEY)
    @GET("images/{image_id}")
    fun getCatImageById(@Path("image_id")image_id: String): Call<CatImage>

    @Headers("x-api-key: " + CatApiPref.CAT_API_KEY)
    @GET("images/search")
    fun getRandomCatImage(@Query("limit")limit: Int): Call<List<CatImage>>

    @Headers("x-api-key: " + CatApiPref.CAT_API_KEY)
    @POST("favourites")
    fun postSaveImageAsFavourites(@Field("image_id") image_id: String): Call<CatResponses>

    @Headers("x-api-key: " + CatApiPref.CAT_API_KEY)
    @POST("/votes")
    fun postCatVote(@Body imageVote: CatVote): Call<CatResponses>
}