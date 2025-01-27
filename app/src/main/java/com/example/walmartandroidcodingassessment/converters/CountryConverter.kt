package com.example.walmartandroidcodingassessment.converters

import com.example.walmartandroidcodingassessment.model.CountryDetailsModel
import com.example.walmartandroidcodingassessment.model.CountryListDetailsModel
import com.example.walmartandroidcodingassessment.model.CurrencyDetailsModel
import com.example.walmartandroidcodingassessment.model.LanguageDetailsModel
import com.example.walmartandroidcodingassessment.network.jsonData.Country

fun List<Country>.convertToCountryDetailsModel() : CountryListDetailsModel {

    val countryModelList = mutableListOf<CountryDetailsModel>()
    this.forEach { country ->
        val currencyDetailsModel = CurrencyDetailsModel(
            code = country.currency?.code,
            name = country.currency?.name,
            symbol = country.currency?.symbol,
        )
        val languageDetailsModel = LanguageDetailsModel(
            code = country.language?.code,
            name = country.language?.name
        )
        val countryDetailsModel = CountryDetailsModel(capital = country.capital,
            code = country.code,
            currency = currencyDetailsModel,
            flag = country.flag,
            language = languageDetailsModel,
            name = country.name,
            region = country.region
        )
        countryModelList.add(countryDetailsModel)
    }
    return CountryListDetailsModel(countries = countryModelList)
}