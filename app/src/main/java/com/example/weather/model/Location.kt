package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class Location(@SerializedName("localtime")
                    val localtime: String = "",
                    @SerializedName("utc_offset")
                    val utcOffset: String = "",
                    @SerializedName("country")
                    val country: String = "",
                    @SerializedName("localtime_epoch")
                    val localtimeEpoch: Int = 0,
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("timezone_id")
                    val timezoneId: String = "",
                    @SerializedName("lon")
                    val lon: String = "",
                    @SerializedName("region")
                    val region: String = "",
                    @SerializedName("lat")
                    val lat: String = "")