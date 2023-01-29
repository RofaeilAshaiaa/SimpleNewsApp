package com.example.newsproject.utils

import java.text.SimpleDateFormat
import java.util.*


fun getDateTimeFormatted(timeEpoch: String?): String? {
    if (timeEpoch != null) {
        val mainDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'", Locale.ENGLISH)
        val newTimeEpoch = timeEpoch.replace('T', ' ', true)
        var unixDate: Date? = null
        try {
            unixDate = mainDateFormat.parse(newTimeEpoch)
        } catch (ex: Exception) {
            return null
        }
        val dateFormat = SimpleDateFormat("EE, dd MMM yyyy  |  hh:mm aa", Locale.ENGLISH)
        return if (unixDate != null) {
            dateFormat.format(unixDate)
        } else {
            null
        }
    } else {
        return null
    }
}