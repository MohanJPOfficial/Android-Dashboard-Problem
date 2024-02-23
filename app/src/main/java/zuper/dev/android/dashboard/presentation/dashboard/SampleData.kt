package zuper.dev.android.dashboard.presentation.dashboard

import zuper.dev.android.dashboard.data.model.JobStatus
import zuper.dev.android.dashboard.domain.model.JobStatsModel
import zuper.dev.android.dashboard.ui.theme.BabyBlue
import zuper.dev.android.dashboard.ui.theme.LightGreen
import zuper.dev.android.dashboard.ui.theme.RedOrange
import zuper.dev.android.dashboard.ui.theme.RedPink
import zuper.dev.android.dashboard.ui.theme.Violet

object SampleData {

    val list = listOf(
        JobStatsModel(
            10, JobStatus.YetToStart, RedPink
        ),
        JobStatsModel(
            5, JobStatus.Incomplete, RedOrange
        ),
        JobStatsModel(
            5, JobStatus.Canceled, BabyBlue
        ),
        JobStatsModel(
            15, JobStatus.InProgress, Violet
        ),
        JobStatsModel(
            25, JobStatus.Completed, LightGreen
        )
    )
}