package com.example.walmartandroidcodingassessment.converters

import com.example.walmartandroidcodingassessment.model.CountryDetailsModel
import com.example.walmartandroidcodingassessment.model.CurrencyDetailsModel
import com.example.walmartandroidcodingassessment.model.LanguageDetailsModel
import com.example.walmartandroidcodingassessment.network.jsonData.Country
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class CountryConverterTest {

    @Test
    fun `convertToCountryDetailsModel should return empty model for empty list`() {
        val emptyCountryList: List<Country> = emptyList()
        val result = emptyCountryList.convertToCountryDetailsModel()
        assertEquals(0, result.countries.size)
    }

    @Test
    fun `convertToCountryDetailsModel should map properties correctly`() {
        val mockCountry = mockk<Country>(relaxed = true)
        every { mockCountry.capital } returns "MockCapital"
        every { mockCountry.code } returns "MC"
        every { mockCountry.currency?.code } returns "CUR"
        every { mockCountry.currency?.name } returns "MockCurrency"
        every { mockCountry.currency?.symbol } returns "$"
        every { mockCountry.flag } returns "MockFlag"
        every { mockCountry.language?.code } returns "LAN"
        every { mockCountry.language?.name } returns "MockLanguage"
        every { mockCountry.name } returns "MockCountry"
        every { mockCountry.region } returns "MockRegion"
        val countryList = listOf(mockCountry)

        val result = countryList.convertToCountryDetailsModel()

        val expectedCurrency = CurrencyDetailsModel(code = "CUR", name = "MockCurrency", symbol = "$")
        val expectedLanguage = LanguageDetailsModel(code = "LAN", name = "MockLanguage")
        val expectedCountry = CountryDetailsModel(
            capital = "MockCapital",
            code = "MC",
            currency = expectedCurrency,
            flag = "MockFlag",
            language = expectedLanguage,
            name = "MockCountry",
            region = "MockRegion"
        )
        result.apply {
            assertEquals(1, countries.size)
            assertEquals(expectedCountry, countries[0])
        }
    }

    @Test
    fun `convertToCountryDetailsModel should handle null fields gracefully`() {
        val mockCountry = mockk<Country>(relaxed = true)
        every { mockCountry.capital } returns null
        every { mockCountry.code } returns null
        every { mockCountry.currency } returns null
        every { mockCountry.flag } returns null
        every { mockCountry.language } returns null
        every { mockCountry.name } returns null
        every { mockCountry.region } returns null
        val countryList = listOf(mockCountry)

        val result = countryList.convertToCountryDetailsModel()

        val expectedCountry = CountryDetailsModel(
            capital = null,
            code = null,
            currency = CurrencyDetailsModel(code = null, name = null, symbol = null),
            flag = null,
            language = LanguageDetailsModel(code = null, name = null),
            name = null,
            region = null
        )
        result.apply {
            assertEquals(1, countries.size)
            assertEquals(expectedCountry, countries[0])
        }
    }
}