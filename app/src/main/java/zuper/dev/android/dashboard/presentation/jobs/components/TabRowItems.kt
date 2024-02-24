package zuper.dev.android.dashboard.presentation.jobs.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.domain.model.JobStatsModel
import zuper.dev.android.dashboard.presentation.jobs.JobsViewModel

@Composable
fun TabRowItems(
    modifier: Modifier = Modifier,
    jobStatsList: List<JobStatsModel>,
    uiAction: (JobsViewModel.UiAction) -> Unit,
    selectedTabIndex: Int
) {

    if(jobStatsList.isEmpty())
        return

    val inactiveColor = Color(0xFF777777)
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp,
        modifier = modifier
            .fillMaxWidth()
    ) {

        jobStatsList.forEachIndexed { index, item ->
            Tab(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                selected = selectedTabIndex == index,
                selectedContentColor = MaterialTheme.colorScheme.onBackground,
                unselectedContentColor = inactiveColor,
                onClick = {

                    uiAction(JobsViewModel.UiAction.OnTabSelected(index, item.jobStatus))
                },
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 15.dp),
                    text = "${item.jobStatus.status} (${item.totalSum})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}