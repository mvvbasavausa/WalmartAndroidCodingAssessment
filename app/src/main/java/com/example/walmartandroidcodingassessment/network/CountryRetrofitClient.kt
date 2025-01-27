package com.example.walmartandroidcodingassessment.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.walmartandroidcodingassessment.network.jsonData.ApiConfig

object CountryRetrofitClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: CountryApiService by lazy {
        retrofit.create(CountryApiService::class.java)
    }
}