package com.example.notesapp.common

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    fun convertDateString(format: String, dateString: String): String {
        return try {
            val inputDateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault())
            val inputDate = inputDateFormat.parse(dateString)

            val outputDateFormat = SimpleDateFormat(format, Locale.getDefault())
            val outputDateString = inputDate?.let { outputDateFormat.format(it) }

            (outputDateString?.format(dateString) ?: Date()).toString()
        } catch (e: ParseException) {
            ""
        }
    }
}