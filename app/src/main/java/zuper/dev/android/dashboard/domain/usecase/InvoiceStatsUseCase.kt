package zuper.dev.android.dashboard.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import zuper.dev.android.dashboard.data.repository.DataRepository
import zuper.dev.android.dashboard.data.model.InvoiceStatus
import zuper.dev.android.dashboard.domain.model.InvoiceStatsModel
import zuper.dev.android.dashboard.ui.theme.Green
import zuper.dev.android.dashboard.ui.theme.Red
import zuper.dev.android.dashboard.ui.theme.SkyBlue
import zuper.dev.android.dashboard.ui.theme.Yellow
import javax.inject.Inject

class InvoiceStatsUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    operator fun invoke(): Flow<List<InvoiceStatsModel>> {

        return dataRepository.observeInvoices().map { invoiceList ->

            val badDebtCount = invoiceList.filter { it.status == InvoiceStatus.BadDebt }.sumOf { it.total }
            val draftCount = invoiceList.filter { it.status == InvoiceStatus.Draft }.sumOf { it.total }
            val paidCount = invoiceList.filter { it.status == InvoiceStatus.Paid }.sumOf { it.total }
            val pendingCount = invoiceList.filter { it.status == InvoiceStatus.Pending }.sumOf { it.total }

            val invoiceStateList = listOf(
                InvoiceStatsModel(badDebtCount, InvoiceStatus.BadDebt, Red),
                InvoiceStatsModel(draftCount, InvoiceStatus.Draft, Yellow),
                InvoiceStatsModel(paidCount, InvoiceStatus.Paid, Green),
                InvoiceStatsModel(pendingCount, InvoiceStatus.Pending, SkyBlue),
            )

            invoiceStateList.sortedBy { it.totalSum }
        }
    }
}