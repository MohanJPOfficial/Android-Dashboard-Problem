package zuper.dev.android.dashboard.presentation.jobs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import zuper.dev.android.dashboard.data.model.JobApiModel
import zuper.dev.android.dashboard.domain.model.JobStatsModel
import zuper.dev.android.dashboard.presentation.jobs.components.JobStatsItem
import zuper.dev.android.dashboard.presentation.jobs.components.TopBar
import zuper.dev.android.dashboard.presentation.util.DateUtils
import zuper.dev.android.dashboard.presentation.util.withHashTag
import zuper.dev.android.dashboard.ui.theme.Purple40

@Composable
fun JobsScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: JobsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            TopBar(
                jobCount = uiState.totalJobCount,
                onBackButtonClick = {
                    navHostController.navigateUp()
                }
            )
            Divider(modifier = Modifier
                .fillMaxWidth()
            )

            JobStatsItem(
                modifier = Modifier
                    .padding(10.dp),
                jobStatsList = uiState.jobStatsList.sortedBy { it.totalSum }
            )
            Divider(modifier = Modifier
                .fillMaxWidth()
            )
            TabRowItems(
                modifier = modifier.fillMaxWidth(),
                jobStatsList = uiState.jobStatsList,
                uiAction = viewModel.accept,
                selectedTabIndex = uiState.selectedTabIndex
            )
            JobItems(jobList = uiState.filteredJobList)
        }
    }
}

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

@Composable
fun JobItems(
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

@Composable
fun JobItem(
    modifier: Modifier = Modifier,
    jobApiModel: JobApiModel
) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(4.dp))
            .border(
                border = BorderStroke(0.1.dp, MaterialTheme.colorScheme.outlineVariant),
                shape = RoundedCornerShape(4.dp),
            )
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 10.dp)
        ) {
            Text(
                text = jobApiModel.jobNumber.toString().withHashTag(),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 5.dp),
                text = jobApiModel.title,
                style = MaterialTheme.typography.titleMedium,
            )

            val formattedDate = DateUtils.getFormattedDate(jobApiModel.startTime, jobApiModel.endTime)

            Text(
                text = formattedDate,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
                fontWeight = FontWeight.Bold
            )
        }
    }
}