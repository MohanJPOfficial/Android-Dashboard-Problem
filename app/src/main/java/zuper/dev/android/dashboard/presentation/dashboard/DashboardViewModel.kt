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
import zuper.dev.android.dashboard.domain.usecase.JobStatsUseCase
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val invoiceStatsUseCase: InvoiceStatsUseCase,
    private val jobStatsUseCase: JobStatsUseCase
): ViewModel() {

    //job
    private val _jobStateListFlow = MutableStateFlow(listOf<JobStatsModel>())
    val jobStateListFlow = _jobStateListFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = listOf()
    )

    //invoice
    private val _invoiceStateListFlow = MutableStateFlow(listOf<InvoiceStatsModel>())
    val invoiceStateListFlow: StateFlow<List<InvoiceStatsModel>> = _invoiceStateListFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    init {

        invoiceStatsUseCase.invoke().onEach { invoiceStateList ->
            _invoiceStateListFlow.update { invoiceStateList }
        }.launchIn(viewModelScope)

        jobStatsUseCase.invoke().onEach { jobStateList ->
            _jobStateListFlow.update { jobStateList }
        }.launchIn(viewModelScope)
    }
}