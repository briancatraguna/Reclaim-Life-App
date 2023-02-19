package com.hacknyu.reclaimlife.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

object DateUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDate(): String {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(LocalDateTime.now())
    }
}