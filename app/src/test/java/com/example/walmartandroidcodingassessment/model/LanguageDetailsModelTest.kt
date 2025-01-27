package com.example.walmartandroidcodingassessment.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class LanguageDetailsModelTest {

    @Test
    fun `test language details model creation`() {
        val language = LanguageDetailsModel(code = "EN", name = "English")
        language.apply {
            assertNotNull(this)
            assertEquals("EN", code)
            assertEquals("English", name)
        }
    }

    @Test
    fun `test null fields`() {
        val language = LanguageDetailsModel(code = null, name = null)
        language.apply {
            assertNotNull(this)
            assertNull(code)
            assertNull(name)
        }
    }
}