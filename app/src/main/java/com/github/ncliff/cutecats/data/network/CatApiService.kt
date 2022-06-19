package com.github.ncliff.cutecats.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatApiService {
    @Headers("x-api-key: " + CatApiPref.CAT_API_KEY)
    @GET("breeds")
    fun getCatBreedsList(): Call<List<CatBreeds>>


    @Headers("x-api-key: " + CatApiPref.CAT_API_KEY)
    @GET("breeds/search")
    fun getCatBreedsByName(@Query("q")name: String): Call<List<CatBreeds>>
}