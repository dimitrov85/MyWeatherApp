package com.example.myweatherapp.ui.views.tabContent

import android.os.Build
import android.util.Log
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.R
import com.example.myweatherapp.model.Hourly
import com.example.myweatherapp.ui.utils.WeatherImage
import com.example.myweatherapp.ui.theme.CustomTextStyle
import com.example.myweatherapp.ui.utils.filterByIndexCondition
import com.example.myweatherapp.ui.utils.formatDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SevenDaysAheadContent(hourly: Hourly?) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        test("dkhc")
        hourly?.let {
            val filteredData = filterByIndexCondition(it.time, it.temperature_2m, it.weathercode, it.windspeed_10m)
            items(filteredData.time.size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = formatDate(filteredData.time[index]),
                            style = CustomTextStyle,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(R.string.details),
                            style = CustomTextStyle,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    WeatherImage(weatherCode = filteredData.weatherCode[index])
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
                                text = "${filteredData.temperature[index]} °C",
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
                                text = "${filteredData.windSpeed[index]} m/s",
                                style = CustomTextStyle,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun test(w: String): String {
    val listW = mutableListOf<Char>()
    w.map {
        listW.add(it)
    }

    return ""
}
