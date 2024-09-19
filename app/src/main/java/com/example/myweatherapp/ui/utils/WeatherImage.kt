package com.example.myweatherapp.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.myweatherapp.R

@Composable
fun WeatherImage(weatherCode: Int) {
    val drawableId = getWeatherDrawable(weatherCode)
    Image(
        painter = painterResource(id = drawableId),
        contentDescription = "Weather Image"
    )
}

private fun getWeatherDrawable(weatherCode: Int): Int {
    return when (weatherCode) {
        0 -> R.drawable.ic_sun
        1 -> R.drawable.ic_sun
        2 -> R.drawable.ic_cloud
        3 -> R.drawable.ic_rain
        45 -> R.drawable.ic_fog
        80 -> R.drawable.ic_thunderstorm
        95 -> R.drawable.ic_thunderstorm
        else -> R.drawable.ic_sun
    }
}
