package com.example.walmartandroidcodingassessment.network

import com.example.walmartandroidcodingassessment.network.jsonData.Country
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: CountryApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test getCountryList returns expected data`(): Unit = runBlocking {
        val mockCountryList = listOf(
            Country(capital = "Washington, D.C.", code = "US", name = "United States", region = "Americas"),
            Country(capital = "Ottawa", code = "CA", name = "Canada", region = "Americas")
        )
        val mockResponse = MockResponse()
            .setBody(Gson().toJson(mockCountryList))
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        val result = apiService.getCountryList()

        result.apply {
            assertNotNull(this)
            assertEquals(2, size)
            assertEquals("United States", this[0].name)
            assertEquals("CA", this[1].code)
        }
    }

    @Test
    fun `test getCountryList handles empty response`(): Unit = runBlocking {
        val mockResponse = MockResponse()
            .setBody("[]")
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        val result = apiService.getCountryList()

        result.apply {
            assertNotNull(this)
            assertEquals(0, size)
        }
    }

    @Test
    fun `test getCountryList handles error response`() = runBlocking {
        val mockResponse = MockResponse().setResponseCode(404)
        mockWebServer.enqueue(mockResponse)

        var exception: Exception? = null
        try {
            apiService.getCountryList()
        } catch (e: Exception) {
            exception = e
        }

        assertNotNull(exception)
    }
}