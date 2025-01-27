package com.example.walmartandroidcodingassessment.network

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryRetrofitClientTest {

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test retrofit instance creation`() {
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(CountryApiService::class.java)

        assertNotNull(apiService)
    }

    @Test
    fun `test apiService instance`() {
        val customRetrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val testApiService = customRetrofit.create(CountryApiService::class.java)

        assertNotNull(testApiService)
    }

    @Test
    fun `test singleton apiService from CountryRetrofitClient`() {
        val apiService = CountryRetrofitClient.apiService
        assertNotNull(apiService)
    }
}