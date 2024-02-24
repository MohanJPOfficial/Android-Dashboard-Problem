package zuper.dev.android.dashboard.domain.usecase

import zuper.dev.android.dashboard.data.model.JobApiModel
import zuper.dev.android.dashboard.data.model.JobStatus
import zuper.dev.android.dashboard.domain.model.JobStatsModel
import zuper.dev.android.dashboard.domain.repository.DataRepository
import zuper.dev.android.dashboard.ui.theme.Green
import zuper.dev.android.dashboard.ui.theme.Purple
import zuper.dev.android.dashboard.ui.theme.Red
import zuper.dev.android.dashboard.ui.theme.SkyBlue
import zuper.dev.android.dashboard.ui.theme.Yellow
import javax.inject.Inject

class JobListUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    operator fun invoke(): List<JobApiModel> {

        return dataRepository.getJobs()
    }

    fun convertToJobStatsList(jobList: List<JobApiModel>): List<JobStatsModel> {
        val yetToStartJobsCount = jobList.filter { it.status == JobStatus.YetToStart }.size
        val inCompleteJobsCount = jobList.filter { it.status == JobStatus.Incomplete }.size
        val cancelledJobsCount = jobList.filter { it.status == JobStatus.Canceled }.size
        val inProgressJobsCount = jobList.filter { it.status == JobStatus.InProgress }.size
        val completedJobsCount = jobList.filter { it.status == JobStatus.Completed }.size

        return listOf(
            JobStatsModel(yetToStartJobsCount, JobStatus.YetToStart, Purple),
            JobStatsModel(inCompleteJobsCount, JobStatus.Incomplete, Red),
            JobStatsModel(cancelledJobsCount, JobStatus.Canceled, Yellow),
            JobStatsModel(inProgressJobsCount, JobStatus.InProgress, SkyBlue),
            JobStatsModel(completedJobsCount, JobStatus.Completed, Green)
        )
    }

    fun filterJobListByJobStatus(
        jobStatus: JobStatus,
        jobList: List<JobApiModel>
    ): List<JobApiModel> {
        return jobList.filter { it.status == jobStatus }
    }
}