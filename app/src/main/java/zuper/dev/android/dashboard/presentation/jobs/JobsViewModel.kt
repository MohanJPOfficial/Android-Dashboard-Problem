package zuper.dev.android.dashboard.presentation.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import zuper.dev.android.dashboard.data.model.JobApiModel
import zuper.dev.android.dashboard.data.model.JobStatus
import zuper.dev.android.dashboard.domain.model.JobStatsModel
import zuper.dev.android.dashboard.domain.usecase.JobListUseCase
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val jobListUseCase: JobListUseCase
): ViewModel() {

    //uiState
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UiState()
    )

    //job list
    private var jobList: List<JobApiModel> = emptyList()

    val accept: (UiAction) -> Unit

    init {
        jobList = jobListUseCase.invoke()

        _uiState.update {
            it.copy(
                totalJobCount = jobList.size
            )
        }

        updateTabIndexAndFilterListByJobStatus(jobStatus = JobStatus.YetToStart)

        updateJobStatsList()

        accept = ::onUiAction
    }

    private fun onUiAction(uiAction: UiAction) {
        when(uiAction) {
            is UiAction.OnTabSelected -> {
                updateTabIndexAndFilterListByJobStatus(uiAction.tabIndex, uiAction.jobStatus)
            }
        }
    }

    private fun updateTabIndexAndFilterListByJobStatus(tabIndex: Int = 0, jobStatus: JobStatus) {
        _uiState.update {
            it.copy(
                selectedTabIndex = tabIndex,
                filteredJobList = jobListUseCase.filterJobListByJobStatus(jobStatus, jobList)
            )
        }
    }

    private fun updateJobStatsList() {
        _uiState.update {
            it.copy(
                jobStatsList = jobListUseCase.convertToJobStatsList(jobList)
            )
        }
    }

    sealed interface UiAction {
        data class OnTabSelected(val tabIndex: Int, val jobStatus: JobStatus): UiAction
    }

    data class UiState(
        val totalJobCount: Int = 0,
        val jobStatsList: List<JobStatsModel> = emptyList(),
        val filteredJobList: List<JobApiModel> = emptyList(),
        val selectedTabIndex: Int = 0
    )
}