package com.github.ncliff.cutecats.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getJSONApi(): CatApiService = mRetrofit.create(CatApiService::class.java)

    companion object {
        private var instance: NetworkService? = null
        const val BASE_URL = "https://api.thecatapi.com/v1/"
        fun getInstance(): NetworkService {
            if (instance == null) {
                instance = NetworkService()
            }
            return instance as NetworkService
        }
    }
}