package zuper.dev.android.dashboard.presentation.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtils {

    fun getTodayDate(): String {
        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d'$' yyyy")

        return date.format(formatter).replace("$", getDayOfMonthSuffix(date.dayOfMonth))
    }

    private fun getDayOfMonthSuffix(n: Int): String {
        if(n in 11..13) {
            return "th"
        }
        return when (n % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }

    fun getFormattedDate(startDateString: String, endDateString: String): String {
        val startLocalDateTime = getLocalDateTime(startDateString)
        val endLocalDateTime = getLocalDateTime(endDateString)


        val startDate = formatDate(startLocalDateTime)
        val startTime = formatTime(startLocalDateTime)
        val endTime = formatTime(endLocalDateTime)

        return if(isSameDate(startLocalDateTime, endLocalDateTime)) {

            "$startDate, $startTime - $endTime"

        } else {

            val endDate = formatDate(endLocalDateTime)
            "$startDate, $startTime -> $endDate, $endTime"
        }
    }

    private fun getLocalDateTime(isoDateString: String): LocalDateTime {

        val isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return LocalDateTime.parse(isoDateString, isoFormatter)
    }

    private fun formatDate(localDateTime: LocalDateTime): String {
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        return when {
            isToday(localDateTime) -> "Today"
            isYesterday(localDateTime) -> "Yesterday"
            else -> localDateTime.format(dateFormatter)
        }
    }

    private fun formatTime(localDateTime: LocalDateTime): String {
        val timeFormatter = DateTimeFormatter
            .ofPattern("hh:mm a")
            .withLocale(Locale.ENGLISH)

        return localDateTime.format(timeFormatter)
    }

    private fun isSameDate(localDateTime1: LocalDateTime, localDateTime2: LocalDateTime): Boolean {
        return localDateTime1.year == localDateTime2.year &&
                localDateTime1.dayOfYear == localDateTime2.dayOfYear
    }

    private fun isToday(localDateTime: LocalDateTime): Boolean {
        val currentLocalTime = LocalDateTime.now()

        return currentLocalTime.year == localDateTime.year && currentLocalTime.dayOfYear == localDateTime.dayOfYear
    }

    private fun isYesterday(localDateTime: LocalDateTime): Boolean {
        val currentLocalTime = LocalDateTime.now()

        return currentLocalTime.year == localDateTime.year && currentLocalTime.dayOfYear.minus(1) == localDateTime.dayOfYear
    }
}