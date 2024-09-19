package com.example.myweatherapp.ui.utils

import android.content.Context
import com.example.myweatherapp.model.City

object SharedPreferencesManager {
    private const val PREFS_NAME = "MyWeatherAppPrefs"
    private const val KEY_SELECTED_CITY = "selected_city"
    private val DEFAULT_CITY_NAME = City.Sofia

    private lateinit var appContext: Context

    fun initialize(context: Context) {
        appContext = context.applicationContext
    }

    // Save selected city to SharedPreferences
    fun saveSelectedCity(city: City) {
        val sharedPreferences = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(KEY_SELECTED_CITY, city.name)
            apply()
        }
    }

    // Get selected city from SharedPreferences, defaulting to Sofia if not set
    fun getSelectedCity(): City {
        val sharedPreferences = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val cityName = sharedPreferences.getString(KEY_SELECTED_CITY, DEFAULT_CITY_NAME.cityName)
        return City.valueOf(cityName ?: DEFAULT_CITY_NAME.cityName)
    }
}


