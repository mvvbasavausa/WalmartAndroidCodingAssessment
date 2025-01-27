package com.example.walmartandroidcodingassessment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.walmartandroidcodingassessment.model.CountryDetailsModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class CountriesAdapterTest {
    private lateinit var adapter: CountriesAdapter
    private lateinit var mockCountryList: List<CountryDetailsModel>
    private lateinit var parent: ViewGroup

    @Mock
    private lateinit var inflater: LayoutInflater

    @Before
    fun setUp() {
    }

    @Test
    fun `adapter returns correct item count`() {
        MockitoAnnotations.openMocks(this)  // Initialize mocks
        mockCountryList = listOf(
            CountryDetailsModel(
                name = "United States",
                region = "Americas",
                code = "US",
                capital = "Washington, D.C."
            ),
            CountryDetailsModel(
                name = "Canada", region = "Americas", code = "CA", capital = "Ottawa"
            ),
            CountryDetailsModel(name = "India", region = "Asia", code = "IN", capital = "New Delhi")
        )
        adapter = CountriesAdapter(mockCountryList)
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        parent = FrameLayout(context) // Mock ViewGroup container

        inflater = mockk(relaxed = true) {
            every { inflate(anyInt(), any(), any()) } returns View(context)
        }

        val itemCount = adapter.itemCount

        assertEquals(mockCountryList.size, itemCount)
    }
}