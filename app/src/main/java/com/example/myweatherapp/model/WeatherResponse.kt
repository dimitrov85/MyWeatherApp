package com.example.myweatherapp.model

data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val generationtime_ms: Double,
    val utc_offset_seconds: Int,
    val timezone: String,
    val timezone_abbreviation: String,
    val elevation: Double,
    val hourly: Hourly
)

data class Hourly(
    val time: List<String>,
    val temperature_2m: List<Double>,
    val weathercode: List<Int>,
    val windspeed_10m: List<Double>
)
