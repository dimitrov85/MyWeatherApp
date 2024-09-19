package com.example.myweatherapp.ui.views

import CustomDropdownMenu
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.R
import com.example.myweatherapp.ui.theme.CustomTextStyle
import com.example.myweatherapp.ui.theme.MyWeatherAppTheme
import com.example.myweatherapp.ui.utils.SharedPreferencesManager
import com.example.myweatherapp.ui.utils.WeatherImage
import com.example.myweatherapp.ui.utils.getCurrentHour
import com.example.myweatherapp.ui.utils.getCurrentTime
import com.example.myweatherapp.ui.viewmodel.WeatherViewModel
import com.example.myweatherapp.ui.views.tabContent.HourByHourContent
import com.example.myweatherapp.ui.views.tabContent.SevenDaysAheadContent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    MyWeatherAppTheme {
        var selectedCity by remember {
            mutableStateOf(
                SharedPreferencesManager.getSelectedCity()
            )
        }
        var expanded by remember { mutableStateOf(false) }
        var selectedTabIndex by remember { mutableIntStateOf(0) }

        LaunchedEffect(selectedCity) {
            viewModel.fetchWeather(selectedCity.latitude, selectedCity.longitude)
        }

        DisposableEffect(selectedCity) {
            onDispose {
                SharedPreferencesManager.saveSelectedCity(selectedCity)
            }
        }

        val weatherData = viewModel.weatherData.collectAsState().value

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomDropdownMenu(
                    selectedCity = selectedCity,
                    onCitySelected = { city ->
                        selectedCity = city
                        viewModel.setSelectedCity(city)
                    }
                )

                Text(
                    text = getCurrentTime(),
                    style = CustomTextStyle,
                    color = Color.Blue,
                    fontSize = 32.sp
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                WeatherImage(
                    weatherCode = weatherData?.hourly?.weathercode?.get(getCurrentHour()) ?: 0
                )

                Spacer(modifier = Modifier.width(16.dp))

                if (weatherData?.hourly?.temperature_2m?.isNotEmpty() == true) {
                    Text(
                        text = "${weatherData.hourly.temperature_2m[getCurrentHour()]}°C",
                        style = CustomTextStyle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                    )
                } else {
                    Text(
                        text = stringResource(R.string.loading),
                        style = CustomTextStyle,
                        fontSize = 18.sp,
                    )
                }
            }

            TabRow(
                selectedTabIndex = selectedTabIndex,
                contentColor = Color.Blue,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.hour_by_hour_today),
                        style = CustomTextStyle,
                        fontSize = 18.sp
                    )
                }
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.seven_days_ahead),
                        style = CustomTextStyle,
                        fontSize = 18.sp
                    )
                }
            }

            when (selectedTabIndex) {
                0 -> HourByHourContent(weatherData?.hourly)
                1 -> SevenDaysAheadContent(weatherData?.hourly)
            }
        }
    }
}
