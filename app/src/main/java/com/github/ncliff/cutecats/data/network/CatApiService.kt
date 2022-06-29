package com.github.ncliff.cutecats.data.network

import com.github.ncliff.cutecats.data.model.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface CatApiService {
    @GET("breeds")
    fun getCatBreedsList(): Call<List<CatBreeds>>

    @GET("breeds/search")
    fun getCatBreedsByName(@Query("q")name: String): Call<List<CatBreeds>>

    @GET("images/{image_id}")
    fun getCatImageById(@Path("image_id")image_id: String): Call<CatImage>

    @GET("images/search")
    fun getRandomCatImage(@Query("limit")limit: Int): Call<List<CatImage>>

    @POST("favourites")
    fun postSaveImageAsFavourites(@Body image: CatFav): Call<CatResponse>

    @POST("votes")
    fun postCatVote(@Body imageVote: CatVote): Call<CatResponse>

    @Multipart
    @POST("images/upload")
    fun postCatUpload(@Part("file") file: RequestBody): Call<CatResponse>

    @GET("favourites")
    fun getCatFavList(): Call<List<CatFav>>
}