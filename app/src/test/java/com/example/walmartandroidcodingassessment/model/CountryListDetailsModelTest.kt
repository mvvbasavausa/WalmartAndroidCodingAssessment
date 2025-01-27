package com.example.walmartandroidcodingassessment.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CountryListDetailsModelTest {

    @Test
    fun `test country list details model creation`() {
        val currency1 = CurrencyDetailsModel(code = "USD", name = "US Dollar", symbol = "$")
        val language1 = LanguageDetailsModel(code = "EN", name = "English")
        val country1 = CountryDetailsModel(
            capital = "Washington, D.C.", code = "US", currency = currency1,
            flag = "US Flag", language = language1, name = "United States", region = "Americas"
        )
        val currency2 = CurrencyDetailsModel(code = "INR", name = "Indian Rupee", symbol = "₹")
        val language2 = LanguageDetailsModel(code = "HI", name = "Hindi")
        val country2 = CountryDetailsModel(
            capital = "New Delhi", code = "IN", currency = currency2,
            flag = "IN Flag", language = language2, name = "India", region = "Asia"
        )
        val countryList = listOf(country1, country2)

        val countryListDetails = CountryListDetailsModel(countries = countryList)

        countryListDetails.apply {
            assertNotNull(this)
            assertEquals(2, countries.size)
            assertEquals(country1, countries[0])
            assertEquals(country2, countries[1])
        }
    }

    @Test
    fun `test empty country list`() {
        val countryListDetails = CountryListDetailsModel(countries = emptyList())

        countryListDetails.apply {
            assertNotNull(this)
            assertEquals(0, countries.size)
        }
    }

    @Test
    fun `test null fields in country list`() {
        val countryListDetails = CountryListDetailsModel(countries = listOf())

        countryListDetails.apply {
            assertNotNull(this)
            assertTrue(countries.isEmpty())
        }
    }
}