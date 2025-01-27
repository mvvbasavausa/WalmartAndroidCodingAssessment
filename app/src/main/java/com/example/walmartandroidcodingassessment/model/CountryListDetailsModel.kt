package com.example.walmartandroidcodingassessment.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryListDetailsModel(
    var countries  : List<CountryDetailsModel>
) : Parcelable