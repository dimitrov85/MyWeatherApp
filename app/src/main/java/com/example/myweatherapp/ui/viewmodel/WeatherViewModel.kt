package com.example.myweatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.api.RetrofitInstance
import com.example.myweatherapp.model.City
import com.example.myweatherapp.model.WeatherResponse
import com.example.myweatherapp.ui.utils.SharedPreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    private var selectedCity: City = SharedPreferencesManager.getSelectedCity()

    init {
        fetchWeather(selectedCity.latitude, selectedCity.longitude)
    }

    fun fetchWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            try {
                val response: WeatherResponse = RetrofitInstance.api.getWeather(latitude, longitude)
                _weatherData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setSelectedCity(city: City) {
        selectedCity = city
        SharedPreferencesManager.saveSelectedCity(city)
        fetchWeather(city.latitude, city.longitude)
    }

    fun getSelectedCity(): City {
        return selectedCity
    }
}
