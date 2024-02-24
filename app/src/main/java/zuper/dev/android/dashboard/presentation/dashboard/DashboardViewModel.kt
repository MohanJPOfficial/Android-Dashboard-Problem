package zuper.dev.android.dashboard.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import zuper.dev.android.dashboard.data.model.InvoiceStatus
import zuper.dev.android.dashboard.data.model.JobStatus
import zuper.dev.android.dashboard.domain.model.InvoiceStatsModel
import zuper.dev.android.dashboard.domain.model.JobStatsModel
import zuper.dev.android.dashboard.domain.usecase.InvoiceStatsUseCase
import zuper.dev.android.dashboard.domain.usecase.JobStatsUseCase
import zuper.dev.android.dashboard.presentation.util.DateUtils
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    jobStatsUseCase: JobStatsUseCase,
    invoiceStatsUseCase: InvoiceStatsUseCase
): ViewModel() {

    //ui state
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UiState()
    )

    init {

        jobStatsUseCase.invoke().onEach { jobStatsList ->
            _uiState.update { uiState ->
                uiState.copy(
                    jobStatsList = jobStatsList,
                    totalJobCount = jobStatsList.sumOf { it.totalSum },
                    completedJobCount = jobStatsList.find { it.jobStatus == JobStatus.Completed }?.totalSum ?: 0
                )
            }
        }.launchIn(viewModelScope)

        invoiceStatsUseCase.invoke().onEach { invoiceStatsList ->
            _uiState.update { uiState ->
                uiState.copy(
                    invoiceStatsList = invoiceStatsList,
                    totalInvoiceValue = invoiceStatsList.sumOf { it.totalSum },
                    collectedInvoiceValue = invoiceStatsList.find { it.invoiceStatus == InvoiceStatus.Paid }?.totalSum ?: 0
                )
            }
        }.launchIn(viewModelScope)
    }

    data class UiState(
        val todayDate: String = DateUtils.getTodayDate(),
        val jobStatsList: List<JobStatsModel> = emptyList(),
        val invoiceStatsList: List<InvoiceStatsModel> = emptyList(),
        val totalJobCount: Int = 0,
        val completedJobCount: Int = 0,
        val totalInvoiceValue: Int = 0,
        val collectedInvoiceValue: Int = 0
    )
}