package com.example.walmartandroidcodingassessment.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class CountryDetailsModelTest {

    @Test
    fun `test country details model creation`() {
        val currency = CurrencyDetailsModel(code = "USD", name = "US Dollar", symbol = "$")
        val language = LanguageDetailsModel(code = "EN", name = "English")

        val country = CountryDetailsModel(
            capital = "Washington, D.C.",
            code = "US",
            currency = currency,
            flag = "US Flag",
            language = language,
            name = "United States of America",
            region = "Americas"
        )

        country.apply {
            assertNotNull(this)
            assertEquals("Washington, D.C.", capital)
            assertEquals("US", code)
            assertNotNull(this.currency)
            assertEquals(currency, this.currency)
            assertEquals("US Flag", flag)
            assertNotNull(this.language)
            assertEquals(language, this.language)
            assertEquals("United States of America", name)
            assertEquals("Americas", region)
        }
    }

    @Test
    fun `test null fields`() {
        val country = CountryDetailsModel()
        country.apply {
            assertNotNull(this)
            assertNull(capital)
            assertNull(code)
            assertNotNull(currency)
            assertNull(currency?.code)
            assertNull(currency?.name)
            assertNull(currency?.symbol)
            assertNull(flag)
            assertNotNull(language)
            assertNull(language?.code)
            assertNull(language?.name)
            assertNull(name)
            assertNull(region)
        }
    }

    @Test
    fun `test nested currency and language`() {
        val currency = CurrencyDetailsModel(code = "EUR", name = "Euro", symbol = "€")
        val language = LanguageDetailsModel(code = "DE", name = "German")

        val country = CountryDetailsModel(
            capital = "Berlin",
            code = "DE",
            currency = currency,
            flag = "DE Flag",
            language = language,
            name = "Germany",
            region = "Europe"
        )

        country.apply {
            assertNotNull(this.currency)
            assertEquals("EUR", this.currency?.code)
            assertEquals("Euro", this.currency?.name)
            assertEquals("€", this.currency?.symbol)
            assertNotNull(this.language)
            assertEquals("DE", this.language?.code)
            assertEquals("German", this.language?.name)
        }
    }
}