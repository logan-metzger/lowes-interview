package com.logan.lowesinterview.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    @Json(name = "cnt")val cnt: Int?,
    @Json(name = "cod")val cod: String?,
    @Json(name = "list")val list: List<HourlyWeather>,
    @Json(name = "message")val message: Int?
)
