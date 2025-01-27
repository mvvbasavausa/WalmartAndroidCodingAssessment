package com.example.walmartandroidcodingassessment.network.jsonData

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class CountryTest {

    private val gson = Gson()

    @Test
    fun `test serialization with all fields`() {
        val country = Country(
            capital = "Washington, D.C.",
            code = "US",
            currency = Currency(code = "USD", name = "United States Dollar", symbol = "$"),
            flag = "https://example.com/flags/us.png",
            language = Language(code = "en", name = "English"),
            name = "United States",
            region = "Americas"
        )

        val json = gson.toJson(country)

        val expectedJson = """
            {
              "capital":"Washington, D.C.",
              "code":"US",
              "currency":{"code":"USD","name":"United States Dollar","symbol":"$"},
              "flag":"https://example.com/flags/us.png",
              "language":{"code":"en","name":"English"},
              "name":"United States",
              "region":"Americas"
            }
        """.trimIndent().replace("\\s".toRegex(), "") // Remove whitespace for comparison
        assertEquals(expectedJson, json.replace("\\s".toRegex(), ""))
    }

    @Test
    fun `test serialization with null fields`() {
        val country = Country()

        val json = gson.newBuilder().serializeNulls().create().toJson(country)

        val expectedJson = """
            {
              "capital":null,
              "code":null,
              "currency":{"code":null,"name":null,"symbol":null},
              "flag":null,
              "language":{"code":null,"name":null},
              "name":null,
              "region":null
            }
        """.trimIndent().replace("\\s".toRegex(), "")
        assertEquals(expectedJson, json.replace("\\s".toRegex(), ""))
    }

    @Test
    fun `test deserialization with all fields`() {
        val json = """
            {
              "capital": "Tokyo",
              "code": "JP",
              "currency": {"code": "JPY", "name": "Japanese Yen", "symbol": "¥"},
              "flag": "https://example.com/flags/jp.png",
              "language": {"code": "ja", "name": "Japanese"},
              "name": "Japan",
              "region": "Asia"
            }
        """.trimIndent()

        val country = gson.fromJson(json, Country::class.java)

        country.apply {
            assertNotNull(this)
            assertEquals("Tokyo", capital)
            assertEquals("JP", code)
            assertNotNull(currency)
            assertEquals("JPY", currency?.code)
            assertEquals("Japanese Yen", currency?.name)
            assertEquals("¥", currency?.symbol)
            assertEquals("https://example.com/flags/jp.png", flag)
            assertNotNull(language)
            assertEquals("ja", language?.code)
            assertEquals("Japanese", language?.name)
            assertEquals("Japan", name)
            assertEquals("Asia", region)
        }
    }

    @Test
    fun `test deserialization with missing fields`() {
        val json = """
            {
              "capital": "Paris",
              "name": "France"
            }
        """.trimIndent()

        val country = gson.fromJson(json, Country::class.java)

        country.apply {
            assertNotNull(this)
            assertEquals("Paris", capital)
            assertEquals("France", name)
            assertEquals(null, code)
            assertNotEquals(null, currency)
            assertEquals(null, currency?.code)
            assertEquals(null, currency?.name)
            assertEquals(null, currency?.symbol)
            assertEquals(null, flag)
            assertNotEquals(null, language)
            assertEquals(null, language?.code)
            assertEquals(null, language?.name)
            assertEquals(null, region)
        }
    }
}