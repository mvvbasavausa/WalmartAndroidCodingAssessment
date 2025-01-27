package com.example.walmartandroidcodingassessment.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryDetailsModel(
    var capital  : String?   = null,
    var code     : String?   = null,
    var currency : CurrencyDetailsModel? = CurrencyDetailsModel(),
    var flag     : String?   = null,
    var language : LanguageDetailsModel? = LanguageDetailsModel(),
    var name     : String?   = null,
    var region   : String?   = null
) : Parcelable