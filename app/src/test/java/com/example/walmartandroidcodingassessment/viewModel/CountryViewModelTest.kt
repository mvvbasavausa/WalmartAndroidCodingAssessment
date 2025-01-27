import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.walmartandroidcodingassessment.model.CountryDetailsModel
import com.example.walmartandroidcodingassessment.model.CountryListDetailsModel
import com.example.walmartandroidcodingassessment.network.CountryApiService
import com.example.walmartandroidcodingassessment.network.CountryRetrofitClient
import com.example.walmartandroidcodingassessment.network.jsonData.Country
import com.example.walmartandroidcodingassessment.viewModel.CountryViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
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
class CountryViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule() // To test LiveData updates

    private lateinit var viewModel: CountryViewModel

    @Before
    fun setup() {
        viewModel = CountryViewModel()
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockitoAnnotations.initMocks(this)
        viewModel = CountryViewModel()
    }

    @Test
    fun `fetchCountryList should post LiveData when API call is successful`() = runTest {
        val countryDetailsModelList = listOf(
            CountryDetailsModel(code = "C1", name = "Country1"),
            CountryDetailsModel(code = "C2", name = "Country2"),
        )
        val mockCountryList = listOf(
            Country(name = "Country1", code = "C1"),
            Country(name = "Country2", code = "C2")
        )
        val mockApiService = mockk<CountryApiService>()
        coEvery { mockApiService.getCountryList() } returns mockCountryList
        mockkObject(CountryRetrofitClient)
        coEvery { CountryRetrofitClient.apiService } returns mockApiService
        val mockObserver = mockk<Observer<CountryListDetailsModel?>>(relaxed = true)
        viewModel.countryData.observeForever(mockObserver)
        viewModel.fetchCountryList()
        verify { mockObserver.onChanged(CountryListDetailsModel(countries = countryDetailsModelList)) }
        viewModel.countryData.removeObserver(mockObserver)
    }

    @Test
    fun `fetchCountryList should post null LiveData when API call throws exception`() = runTest {
        val mockApiService = mockk<CountryApiService>()
        coEvery { mockApiService.getCountryList() } throws Exception("Network error")
        mockkObject(CountryRetrofitClient)
        coEvery { CountryRetrofitClient.apiService } returns mockApiService
        val mockObserver = mockk<Observer<CountryListDetailsModel?>>(relaxed = true)
        viewModel.countryData.observeForever(mockObserver)
        viewModel.fetchCountryList()
        verify { mockObserver.onChanged(null) }
        viewModel.countryData.removeObserver(mockObserver)
    }
}