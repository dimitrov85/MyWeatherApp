package com.example.myweatherapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myweatherapp.ui.theme.Beige
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme
import com.example.myweatherapp.ui.viewmodel.WeatherViewModel
import com.example.myweatherapp.ui.views.WeatherScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyWeatherApp(weatherViewModel: WeatherViewModel) {
    MyWeatherAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Beige
        ) {
            WeatherScreen(weatherViewModel)
        }
    }
}
