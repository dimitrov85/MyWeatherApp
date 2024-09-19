package com.example.myweatherapp.ui.utils
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myweatherapp.model.FilteredDateTempAndCode
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
    val date = LocalDate.parse(dateString, formatter)

    val dayOfMonth = date.dayOfMonth
//    val month = date.month.getDisplayName(TextStyle.Default, Locale.getDefault())
    val monthFullName = getMonthFullName(date.monthValue)

    return "$dayOfMonth $monthFullName"
}

fun getMonthFullName(monthValue: Int): String {
    return when (monthValue) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> throw IllegalArgumentException("Invalid month value: $monthValue")
    }
}

fun filterByIndexCondition(time: List<String>?, temperature: List<Double>?, weatherCode: List<Int>?, windSpeed: List<Double>?): FilteredDateTempAndCode {
    val indices = time?.mapIndexedNotNull { index, _ -> if (index == 11 || (index > 11 && (index - 11) % 24 == 0)) index else null } ?: emptyList()

    val filteredTime = indices.map { time!![it] }
    val filteredTemperature = indices.map { temperature!![it] }
    val filteredWeatherCode = indices.map { weatherCode!![it] }
    val filteredWindSpeed = indices.map { windSpeed!![it] }

    return FilteredDateTempAndCode(filteredTime, filteredTemperature, filteredWeatherCode, filteredWindSpeed)
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatHour(dateTimeStr: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
    val outputFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val dateTime = LocalDateTime.parse(dateTimeStr, inputFormatter)
    return dateTime.format(outputFormatter)
}

fun getCurrentHour(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.HOUR_OF_DAY) // 24-hour format
}

fun getCurrentTime(): String {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    return String.format("%02d:%02d", hour, minute)
}




