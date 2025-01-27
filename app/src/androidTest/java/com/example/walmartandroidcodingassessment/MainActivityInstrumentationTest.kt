package com.example.walmartandroidcodingassessment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.walmartandroidcodingassessment.view.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentationTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.walmartandroidcodingassessment", appContext.packageName)
    }

    @Test
    fun verify_that_some_views_are_populated_and_country_details_are_not_populated_initially() {
        onView(withText("Welcome to the Countries App")).check(matches(isDisplayed()))
        onView(withText("Get Data")).check(matches(isDisplayed()))
        onView(withId(R.id.country_list)).check(matches(not(isDisplayed())))
    }

    @Test
    fun verify_that_country_details_are_populated_when_getDataButton_is_clicked() {
        onView(withText("Welcome to the Countries App")).check(matches(isDisplayed()))
        onView(withText("Get Data")).check(matches(isDisplayed()))
        Thread.sleep(ONE_SECOND)
        onView(withText("Get Data")).perform(click())
        Thread.sleep(ONE_SECOND)
        onView(withId(R.id.country_list)).check(matches(isDisplayed()))
        onView(withId(R.id.country_list)).check(matches(hasMinimumChildCount(1)))
        onView(withId(R.id.country_list)).check(matches(hasChildCount(9)))
    }


    @Test
    fun verify_that_country_details_are_populated_when_getDataButton_is_clicked_and_app_is_Relaunched() {
        onView(withText("Welcome to the Countries App")).check(matches(isDisplayed()))
        onView(withText("Get Data")).check(matches(isDisplayed()))
        Thread.sleep(ONE_SECOND)
        onView(withText("Get Data")).perform(click())
        Thread.sleep(ONE_SECOND)
        onView(withId(R.id.country_list)).check(matches(isDisplayed()))
        onView(withId(R.id.country_list)).check(matches(hasMinimumChildCount(1)))
        onView(withId(R.id.country_list)).check(matches(hasChildCount(9)))
        Thread.sleep(ONE_SECOND)
        activityScenarioRule.scenario.recreate()
        Thread.sleep(ONE_SECOND)
        onView(withText("Welcome to the Countries App")).check(matches(isDisplayed()))
        onView(withText("Get Data")).check(matches(isDisplayed()))
    }

    companion object {
        private const val ONE_SECOND = 1000L
    }
}