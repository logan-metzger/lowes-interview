package com.logan.lowesinterview.repos

import com.logan.lowesinterview.models.ApiResponse
import com.logan.lowesinterview.remote.WeatherManager
import retrofit2.Response

object WeatherRepository {
    suspend fun getWeather(city: String): Response<ApiResponse> {
        return WeatherManager().getWeather(city)
    }
}