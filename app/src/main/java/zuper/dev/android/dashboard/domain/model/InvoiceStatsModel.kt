package zuper.dev.android.dashboard.domain.model

import androidx.compose.ui.graphics.Color
import zuper.dev.android.dashboard.data.model.InvoiceStatus

data class InvoiceStatsModel(
    val totalSum: Int,
    val invoiceStatus: InvoiceStatus,
    val color: Color
)