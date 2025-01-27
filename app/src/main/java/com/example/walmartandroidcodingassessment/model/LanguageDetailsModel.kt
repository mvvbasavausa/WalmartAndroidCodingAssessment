package com.example.walmartandroidcodingassessment.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LanguageDetailsModel (
    var code : String? = null,
    var name : String? = null
) : Parcelable