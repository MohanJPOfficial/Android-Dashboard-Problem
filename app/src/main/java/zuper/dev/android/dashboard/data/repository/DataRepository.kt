package zuper.dev.android.dashboard.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import zuper.dev.android.dashboard.data.model.InvoiceApiModel
import zuper.dev.android.dashboard.data.model.JobApiModel
import zuper.dev.android.dashboard.data.remote.ApiDataSource

class DataRepository(private val apiDataSource: ApiDataSource) {

    /**
     * This API returns jobs in realtime using which stats can be computed
     */
    fun observeJobs(): Flow<List<JobApiModel>> {
        return apiDataSource.observeJobs()
    }

    /**
     * This API returns invoices in realtime using which stats can be computed
     */
    fun observeInvoices(): Flow<List<InvoiceApiModel>> {
        return apiDataSource.observeInvoices()
    }

    /**
     * This API returns random jobs every time invoked
     */
    fun getJobs(): List<JobApiModel> {
        // TODO - Update as per listing page expectations
        return apiDataSource.getJobs()
    }
}