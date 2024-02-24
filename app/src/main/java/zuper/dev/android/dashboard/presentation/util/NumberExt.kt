package zuper.dev.android.dashboard.presentation.util

import java.text.NumberFormat
import java.util.Locale

fun Int.withComma(): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)

    return numberFormat.format(this)
}