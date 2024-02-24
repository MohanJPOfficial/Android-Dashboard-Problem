package zuper.dev.android.dashboard.domain.repository

import kotlinx.coroutines.flow.Flow
import zuper.dev.android.dashboard.data.model.InvoiceApiModel
import zuper.dev.android.dashboard.data.model.JobApiModel

interface DataRepository {

    /**
     * This API returns jobs in realtime using which stats can be computed
     */
    fun observeJobs(): Flow<List<JobApiModel>>

    /**
     * This API returns invoices in realtime using which stats can be computed
     */
    fun observeInvoices(): Flow<List<InvoiceApiModel>>

    /**
     * This API returns random jobs every time invoked
     */
    fun getJobs(): List<JobApiModel>
}