package com.example.walmartandroidcodingassessment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walmartandroidcodingassessment.converters.convertToCountryDetailsModel
import com.example.walmartandroidcodingassessment.model.CountryListDetailsModel
import com.example.walmartandroidcodingassessment.network.CountryRetrofitClient

class CountryViewModel : ViewModel() {
    private val _countryData = MutableLiveData<CountryListDetailsModel?>()
    val countryData: LiveData<CountryListDetailsModel?> = _countryData
    suspend fun fetchCountryList() {
        try {
            val response = CountryRetrofitClient.apiService.getCountryList()
            _countryData.postValue(response.convertToCountryDetailsModel())
        } catch (exception: Exception) {
            // "Failed to load countries: ${e.message}"
            _countryData.postValue(null)
        }
    }
}