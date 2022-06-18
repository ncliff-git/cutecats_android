package com.github.ncliff.cutecats.data.network

import retrofit2.Call
import retrofit2.http.*

interface CatApiService {
    @Headers("x-api-key" + CatApiPref.CAT_API_KEY)
    @GET("breeds")
    fun getCatBreedsList(): Call<List<CatBreeds>>


    @Headers("x-api-key=" + CatApiPref.CAT_API_KEY)
    @GET("breeds/search?name={name}")
    fun getCatBreedsByName(@Path("name")name: String): Call<CatBreeds>
}