package com.example.walmartandroidcodingassessment.view

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.walmartandroidcodingassessment.R
import com.example.walmartandroidcodingassessment.model.CountryDetailsModel
import com.example.walmartandroidcodingassessment.model.CountryListDetailsModel
import com.example.walmartandroidcodingassessment.model.CurrencyDetailsModel
import com.example.walmartandroidcodingassessment.model.LanguageDetailsModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityUnitTest2 {
    @get:Rule
    var activityRule = activityScenarioRule<MainActivity>()

    private val currency1 = CurrencyDetailsModel(code = "USD", name = "US Dollar", symbol = "$")
    private val language1 = LanguageDetailsModel(code = "EN", name = "English")
    private val country1 = CountryDetailsModel(
        capital = "Washington, D.C.", code = "US", currency = currency1,
        flag = "US Flag", language = language1, name = "United States", region = "Americas"
    )

    private val currency2 = CurrencyDetailsModel(code = "INR", name = "Indian Rupee", symbol = "₹")
    private val language2 = LanguageDetailsModel(code = "HI", name = "Hindi")
    private val country2 = CountryDetailsModel(
        capital = "New Delhi", code = "IN", currency = currency2,
        flag = "IN Flag", language = language2, name = "India", region = "Asia"
    )

    // Arrange
    private val mockCountryList = listOf(country1, country2)
    private val mockCountryListDetailsModel = CountryListDetailsModel(mockCountryList)

    @Before
    fun setUp() {
    }

    @Test
    fun test_activity_is_initialized_correctly() {
        val scenario = activityRule.scenario
        scenario.onActivity { activity ->
            assertNotNull(activity)
            val getDataButton = activity.findViewById<Button>(R.id.get_data_button)
            assertEquals(View.VISIBLE, getDataButton.visibility)
            val headerText = activity.findViewById<TextView>(R.id.headerText)
            assertEquals(View.VISIBLE, headerText.visibility)
        }
    }

    @Test
    fun test_updateUI_updates_the_recyclerView_properly() {
        val scenario = activityRule.scenario
        scenario.onActivity { activity ->
            activity.updateUI(mockCountryListDetailsModel)
            val recyclerView = activity.findViewById<RecyclerView>(R.id.country_list)
            assertNotNull(recyclerView.adapter)
            assertEquals(2, recyclerView.adapter?.itemCount)
        }
    }
}