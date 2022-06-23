package com.github.ncliff.cutecats.data.network

import com.github.ncliff.cutecats.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    @FormUrlEncoded
    @Headers("x-api-key: " + CatApiPref.CAT_API_KEY)
    @POST("favourites")
    fun postSaveImageAsFavourites(@Field("image_id") image_id: String, @Field("sub_id") sub_id: String?): Call<CatResponses>

    @Headers("x-api-key: " + CatApiPref.CAT_API_KEY)
    @POST("votes")
    fun postCatVote(@Body imageVote: CatVote): Call<CatResponses>

    @Headers("x-api-key: " + CatApiPref.CAT_API_KEY)
    @POST("images/upload")
    fun postCatUpload(@Body file: RequestBody): Call<UploadResponse>
}