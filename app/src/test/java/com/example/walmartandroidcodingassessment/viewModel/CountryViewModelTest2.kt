import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.walmartandroidcodingassessment.converters.convertToCountryDetailsModel
import com.example.walmartandroidcodingassessment.model.CountryDetailsModel
import com.example.walmartandroidcodingassessment.model.CountryListDetailsModel
import com.example.walmartandroidcodingassessment.network.CountryRetrofitClient
import com.example.walmartandroidcodingassessment.network.jsonData.Country
import com.example.walmartandroidcodingassessment.viewModel.CountryViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.unmockkObject
import io.mockk.unmockkStatic
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@OptIn(ExperimentalCoroutinesApi::class)
class CountryViewModelTest2 {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CountryViewModel

    @Before
    fun setup() {
        viewModel = CountryViewModel()
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `fetchCountryList should post LiveData when API call is successful again`(): Unit =
        runBlocking {
            mockkObject(CountryRetrofitClient)
            val countryList = listOf(
                Country(name = "Country1", code = "C1"),
                Country(name = "Country2", code = "C2")
            )
            val expectedData = CountryListDetailsModel(
                countries = listOf(
                    CountryDetailsModel(name = "Country1", code = "C1"),
                    CountryDetailsModel(name = "Country2", code = "C2")
                )
            )
            coEvery { CountryRetrofitClient.apiService.getCountryList() } returns countryList
            mockkStatic("com.example.walmartandroidcodingassessment.converters.CountryConverterKt") // Replace with actual package
            every { countryList.convertToCountryDetailsModel() } returns expectedData
            val observer = mockk<Observer<CountryListDetailsModel?>>(relaxed = true)
            viewModel.countryData.observeForever(observer)
            viewModel.fetchCountryList()
            verify { observer.onChanged(expectedData) }
            viewModel.countryData.removeObserver(observer)
            unmockkStatic("com.example.walmartandroidcodingassessment.converters.CountryConverterKt")
            unmockkObject(CountryRetrofitClient)
        }

    @Test
    fun `fetchCountryList should post null to LiveData when API call fails again`(): Unit =
        runBlocking {
            mockkObject(CountryRetrofitClient)
            coEvery { CountryRetrofitClient.apiService.getCountryList() } throws RuntimeException("API error")
            val observer = mockk<Observer<CountryListDetailsModel?>>(relaxed = true)
            viewModel.countryData.observeForever(observer)
            viewModel.fetchCountryList()
            verify { observer.onChanged(null) }
            viewModel.countryData.removeObserver(observer)
        }
}