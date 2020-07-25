package com.yadu.appdemo.data.api

import com.yadu.appdemo.model.Weather
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("onecall?")
    fun getWeatherResults(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<Weather>
}