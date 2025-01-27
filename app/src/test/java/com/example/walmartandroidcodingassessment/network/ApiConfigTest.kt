package com.example.walmartandroidcodingassessment.network

import com.example.walmartandroidcodingassessment.network.jsonData.ApiConfig
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiConfigTest {

    @Test
    fun `test BASE_URL value`() {
        val expectedBaseUrl = "https://gist.githubusercontent.com/peymano-wmt/"
        assertEquals(expectedBaseUrl, ApiConfig.BASE_URL)
    }
}