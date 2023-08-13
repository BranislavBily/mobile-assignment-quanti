package com.branislavbily.rocket.core.domain

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat

interface DateFormatter {
    /**
     * Changes the format of the String to format required
     *
     * @param date Date in String in given format
     * @return Formatted date in String
     */
    fun format(date: String): String
}

class CustomDateTimeFormatter : DateFormatter {

    private val apiDateFormat = "yyyy-MM-dd"
    private val apiDateFormatter: DateFormat = SimpleDateFormat(apiDateFormat, java.util.Locale.US)
    private val appDateFormat = "d.M.yyyy"
    private val appDateFormatter: DateFormat = SimpleDateFormat(appDateFormat, java.util.Locale.US)

    override fun format(date: String): String {
        val dateDate = apiDateFormatter.parse(date)
        return appDateFormatter.format(dateDate)
    }
}
