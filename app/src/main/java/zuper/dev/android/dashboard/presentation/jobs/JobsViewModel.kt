package zuper.dev.android.dashboard.presentation.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import zuper.dev.android.dashboard.data.DataRepository
import zuper.dev.android.dashboard.data.model.JobApiModel
import zuper.dev.android.dashboard.domain.model.JobStatsModel
import zuper.dev.android.dashboard.domain.usecase.JobStatsUseCase
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val jobStatsUseCase: JobStatsUseCase,
    private val dataRepository: DataRepository
): ViewModel() {

    //job stats
    private val _jobStateListFlow = MutableStateFlow(listOf<JobStatsModel>())
    val jobStateListFlow = _jobStateListFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    //job list
    private val _jobListFlow = MutableStateFlow(listOf<JobApiModel>())
    val jobListFlow = _jobListFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    init {
        _jobStateListFlow.update { jobStatsUseCase.invoke() }

        _jobListFlow.update { dataRepository.getJobs() }
    }
}