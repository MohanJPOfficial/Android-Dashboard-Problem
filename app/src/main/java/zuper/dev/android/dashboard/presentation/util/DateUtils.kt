package zuper.dev.android.dashboard.presentation.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtils {

    fun getTodayDate(): String {
        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d'$' yyyy")

        return date.format(formatter).replace("$", getDayOfMonthSuffix(date.dayOfMonth))
    }

    private fun getDayOfMonthSuffix(n: Int): String {
        if (n in 11..13) {
            return "th"
        }
        return when (n % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }
}