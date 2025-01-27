package com.example.walmartandroidcodingassessment.network.jsonData

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class LanguageTest {

    private val gson = Gson()

    @Test
    fun `test serialization with all fields`() {
        val language = Language(
            code = "en",
            name = "English"
        )
        val expectedJson = """{"code":"en","name":"English"}"""

        val json = gson.toJson(language)

        assertEquals(expectedJson, json)
    }

    @Test
    fun `test serialization with null fields`() {
        val language = Language()
        val expectedJson = """{"code":null,"name":null}"""

        val json = gson.newBuilder().serializeNulls().create().toJson(language)

        assertEquals(expectedJson, json)
    }

    @Test
    fun `test deserialization with all fields`() {
        val json = """{"code":"fr","name":"French"}"""

        val language = gson.fromJson(json, Language::class.java)

        language.apply {
            assertNotNull(this)
            assertEquals("fr", code)
            assertEquals("French", name)
        }
    }

    @Test
    fun `test deserialization with missing fields`() {
        val json = """{"name":"Spanish"}"""

        val language = gson.fromJson(json, Language::class.java)

        language.apply {
            assertNotNull(this)
            assertEquals(null, code)
            assertEquals("Spanish", name)
        }
    }

    @Test
    fun `test deserialization with null fields`() {
        val json = """{"code":null,"name":null}"""

        val language = gson.fromJson(json, Language::class.java)

        language.apply {
            assertNotNull(this)
            assertEquals(null, code)
            assertEquals(null, name)
        }
    }
}