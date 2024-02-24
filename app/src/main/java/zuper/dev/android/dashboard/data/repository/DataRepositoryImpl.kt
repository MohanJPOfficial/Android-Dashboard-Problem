package zuper.dev.android.dashboard.data.repository

import kotlinx.coroutines.flow.Flow
import zuper.dev.android.dashboard.data.model.InvoiceApiModel
import zuper.dev.android.dashboard.data.model.JobApiModel
import zuper.dev.android.dashboard.data.remote.ApiDataSource
import zuper.dev.android.dashboard.domain.repository.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val apiDataSource: ApiDataSource
): DataRepository {

    /**
     * This API returns jobs in realtime using which stats can be computed
     */
    override fun observeJobs(): Flow<List<JobApiModel>> {
        return apiDataSource.observeJobs()
    }

    /**
     * This API returns invoices in realtime using which stats can be computed
     */
    override fun observeInvoices(): Flow<List<InvoiceApiModel>> {
        return apiDataSource.observeInvoices()
    }

    /**
     * This API returns random jobs every time invoked
     */
    override fun getJobs(): List<JobApiModel> {
        return apiDataSource.getJobs()
    }
}
