package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class Request(@SerializedName("unit")
                   val unit: String = "",
                   @SerializedName("query")
                   val query: String = "",
                   @SerializedName("language")
                   val language: String = "",
                   @SerializedName("type")
                   val type: String = "")