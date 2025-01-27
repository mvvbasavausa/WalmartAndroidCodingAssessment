package com.example.walmartandroidcodingassessment.network

import com.example.walmartandroidcodingassessment.network.jsonData.Country
import retrofit2.http.GET

interface CountryApiService {
    @GET("32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountryList(): List<Country>
}