package com.example.walmartandroidcodingassessment.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrencyDetailsModel(
    var code : String? = null,
    var name : String? = null,
    var symbol : String? = null
) : Parcelable

