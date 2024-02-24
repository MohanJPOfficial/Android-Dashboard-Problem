package zuper.dev.android.dashboard.presentation.jobs.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import zuper.dev.android.dashboard.data.model.JobApiModel

@Composable
fun JobItemList(
    modifier: Modifier = Modifier,
    jobList: List<JobApiModel>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(jobList) { jobApiModel ->
            JobItem(jobApiModel = jobApiModel)
        }
    }
}