package com.example.myweatherapp.model

data class FilteredDateTempAndCode(
    val time: List<String>,
    val temperature: List<Double>,
    val weatherCode: List<Int>,
    val windSpeed: List<Double>
)