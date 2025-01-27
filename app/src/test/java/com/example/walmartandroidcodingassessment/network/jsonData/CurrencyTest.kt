package com.example.walmartandroidcodingassessment.network.jsonData

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class CurrencyTest {

    private val gson = Gson()

    @Test
    fun `test serialization of Currency`() {
        val currency = Currency(
            code = "USD",
            name = "US Dollar",
            symbol = "$"
        )
        val expectedJson = """{"code":"USD","name":"US Dollar","symbol":"$"}"""

        val json = gson.toJson(currency)

        assertEquals(expectedJson, json)
    }

    @Test
    fun `test deserialization of Currency`() {
        val json = """{"code":"USD","name":"US Dollar","symbol":"$"}"""

        val currency = gson.fromJson(json, Currency::class.java)

        currency.apply {
            assertNotNull(this)
            assertEquals("USD", code)
            assertEquals("US Dollar", name)
            assertEquals("$", symbol)
        }
    }

    @Test
    fun `test deserialization with null fields`() {
        val json = """{"code":null,"name":null,"symbol":null}"""

        val currency = gson.fromJson(json, Currency::class.java)

        currency.apply {
            assertNotNull(this)
            assertNull(code)
            assertNull(name)
            assertNull(symbol)
        }
    }

    @Test
    fun `test serialization with null fields`() {
        val currency = Currency()
        val expectedJson = """{"code":null,"name":null,"symbol":null}"""
        val gsonWithNulls = Gson().newBuilder().serializeNulls().create()

        val json = gsonWithNulls.toJson(currency)

        assertEquals(expectedJson, json)
    }

    @Test
    fun `test deserialization with empty JSON`() {
        val json = """{}"""

        val currency = gson.fromJson(json, Currency::class.java)

        currency.apply {
            assertNotNull(this)
            assertNull(code)
            assertNull(name)
            assertNull(symbol)
        }
    }
}