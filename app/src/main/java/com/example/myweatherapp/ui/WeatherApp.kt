package com.example.myweatherapp.ui
import android.app.Application
import com.example.myweatherapp.ui.utils.SharedPreferencesManager

class WeatherApp: Application() {

        override fun onCreate() {
            super.onCreate()
            SharedPreferencesManager.initialize(this)
        }

}