package com.example.submissioncompose.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.O)
fun timeAgo(isoDate: String): String {
    val dateTime = Instant.parse(isoDate)
    val now = Instant.now()
    val seconds = ChronoUnit.SECONDS.between(dateTime, now)

    return when {
        seconds < 60 -> "$seconds seconds ago"
        seconds < 3600 -> "${TimeUnit.SECONDS.toMinutes(seconds)} minutes ago"
        seconds < 86400 -> "${TimeUnit.SECONDS.toHours(seconds)} hours ago"
        else -> "${TimeUnit.SECONDS.toDays(seconds)} days ago"
    }
}