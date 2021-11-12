package com.logan.lowesinterview.remote

import com.logan.lowesinterview.models.ApiResponse
import com.logan.lowesinterview.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

class WeatherManager {
    private val service: WeatherService
    private val retrofit = RetrofitService.providesRetrofitService()

    init {
        service = retrofit.create(WeatherService::class.java)
    }

    suspend fun getWeather(city: String) = service.getWeather(city, Constants.API_KEY)

    interface WeatherService {
        @GET("/data/2.5/forecast")
        suspend fun getWeather(
            @Query("q") city: String,
            @Query("appId") apiKey: String,
            @Query("units") units: String = "imperial"
        ): Response<ApiResponse>
    }
}