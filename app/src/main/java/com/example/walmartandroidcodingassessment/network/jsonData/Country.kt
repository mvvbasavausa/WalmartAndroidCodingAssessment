package com.example.walmartandroidcodingassessment.network.jsonData

import com.google.gson.annotations.SerializedName

data class Country (
    @SerializedName("capital")
    var capital : String?   = null,
    @SerializedName("code")
    var code : String?   = null,
    @SerializedName("currency")
    var currency : Currency? = Currency(),
    @SerializedName("flag")
    var flag : String?   = null,
    @SerializedName("language")
    var language : Language? = Language(),
    @SerializedName("name")
    var name : String?   = null,
    @SerializedName("region")
    var region : String?   = null
)