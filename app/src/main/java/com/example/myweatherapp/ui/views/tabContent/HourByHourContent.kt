package com.example.myweatherapp.ui.views.tabContent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.R
import com.example.myweatherapp.model.Hourly
import com.example.myweatherapp.ui.utils.WeatherImage
import com.example.myweatherapp.ui.theme.CustomTextStyle
import com.example.myweatherapp.ui.utils.formatHour

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourByHourContent(hourly: Hourly?) {
    if (hourly != null) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(hourly.temperature_2m.take(24).size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = formatHour(hourly.time[index]),
                        style = CustomTextStyle,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    WeatherImage(weatherCode = hourly.weathercode[index])
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.ic_sun_thermo),
                                contentDescription = "Thermometer",
                                Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${hourly.temperature_2m[index]} °C",
                                style = CustomTextStyle,
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.ic_wind),
                                contentDescription = "Wind",
                                Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${hourly.windspeed_10m[index]} m/s",
                                style = CustomTextStyle,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    } else {
        Text(text = "Loading hour by hour data...")
    }
}
