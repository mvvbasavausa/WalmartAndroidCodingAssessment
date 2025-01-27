package com.example.walmartandroidcodingassessment.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CurrencyDetailsModelTest {

    @Test
    fun `test default initialization`() {
        val currency = CurrencyDetailsModel()
        currency.apply {
            assertNull(code)
            assertNull(name)
            assertNull(symbol)
        }
    }

    @Test
    fun `test property assignment`() {
        val currency = CurrencyDetailsModel(code = "USD", name = "United States Dollar", symbol = "$")
        currency.apply {
            assertEquals("USD", code)
            assertEquals("United States Dollar", name)
            assertEquals("$", symbol)
        }
    }

    @Test
    fun `test equality`() {
        val currency1 = CurrencyDetailsModel(code = "USD", name = "United States Dollar", symbol = "$")
        val currency2 = CurrencyDetailsModel(code = "USD", name = "United States Dollar", symbol = "$")
        assertEquals(currency1, currency2)
    }

    @Test
    fun `test hashcode consistency`() {
        val currency = CurrencyDetailsModel(code = "USD", name = "United States Dollar", symbol = "$")
        val sameCurrency = CurrencyDetailsModel(code = "USD", name = "United States Dollar", symbol = "$")
        assertEquals(currency.hashCode(), sameCurrency.hashCode())
    }
}