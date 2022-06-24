package com.github.ncliff.cutecats.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    private val httpClientBuilder = OkHttpClient.Builder()

    init {
        httpClientBuilder.addInterceptor(Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.addHeader("x-api-key", CatApiPref.CAT_API_KEY)
            chain.proceed(requestBuilder.build())
        })
    }

    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClientBuilder.build())
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