package zuper.dev.android.dashboard.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import zuper.dev.android.dashboard.domain.model.InvoiceStatsModel
import zuper.dev.android.dashboard.domain.model.JobStatsModel
import zuper.dev.android.dashboard.domain.usecase.InvoiceStatsUseCase
import zuper.dev.android.dashboard.domain.usecase.RealtimeJobStatsUseCase
import zuper.dev.android.dashboard.presentation.util.DateUtils
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    realtimeJobStatsUseCase: RealtimeJobStatsUseCase,
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

        realtimeJobStatsUseCase.invoke().onEach { jobStatsList ->
            _uiState.update {
                it.copy(
                    jobStatsList = jobStatsList
                )
            }
        }.launchIn(viewModelScope)

        invoiceStatsUseCase.invoke().onEach { invoiceStatsList ->
            _uiState.update {
                it.copy(
                    invoiceStatsList = invoiceStatsList
                )
            }
        }.launchIn(viewModelScope)
    }

    data class UiState(
        val todayDate: String = DateUtils.getTodayDate(),
        val jobStatsList: List<JobStatsModel> = emptyList(),
        val invoiceStatsList: List<InvoiceStatsModel> = emptyList()
    )
}