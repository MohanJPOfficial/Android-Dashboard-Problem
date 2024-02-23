package zuper.dev.android.dashboard.domain.model

import androidx.compose.ui.graphics.Color
import zuper.dev.android.dashboard.data.model.JobStatus

data class JobStatsModel(
    val totalSum: Int,
    val jobStatus: JobStatus,
    val color: Color
)