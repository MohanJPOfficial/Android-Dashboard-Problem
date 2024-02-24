package zuper.dev.android.dashboard.presentation.jobs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import zuper.dev.android.dashboard.data.model.JobApiModel
import zuper.dev.android.dashboard.domain.model.JobStatsModel
import zuper.dev.android.dashboard.presentation.dashboard.SampleData
import zuper.dev.android.dashboard.presentation.jobs.components.JobStatsItem
import zuper.dev.android.dashboard.presentation.jobs.components.TopBar
import zuper.dev.android.dashboard.presentation.util.DateUtils

@Composable
fun JobsScreen(
    modifier: Modifier = Modifier,
    jobsViewModel: JobsViewModel = hiltViewModel()
) {
    val jobsStatsList by jobsViewModel.jobStateListFlow.collectAsStateWithLifecycle()
    val jobList by jobsViewModel.jobListFlow.collectAsStateWithLifecycle()

    Surface(
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            TopBar(
                jobCount = jobList.size
            )
            Divider(modifier = Modifier
                .fillMaxWidth(),
                color = Color.LightGray
            )
            JobStatsItem(
                modifier = Modifier
                    .padding(10.dp),
                jobStatsList = jobsStatsList
            )
            Divider(modifier = Modifier
                .fillMaxWidth(),
                color = Color.LightGray
            )
            TabRowItems(
                modifier = modifier.fillMaxWidth(),
                jobStatsList = jobsStatsList
            )
            JobItems(jobList = jobList)
        }
    }
}

@Composable
fun TabRowItems(
    modifier: Modifier = Modifier,
    jobStatsList: List<JobStatsModel>
) {

    if(jobStatsList.isEmpty())
        return

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val inactiveColor = Color(0xFF777777)
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        contentColor = Color.Black,
        modifier = modifier
            .padding(vertical = 10.dp)
    ) {

        jobStatsList.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                },
            ) {
                Text(
                    text = "${item.jobStatus} (${item.totalSum})",
                    modifier = Modifier
                        .padding(start = 10.dp),
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
    Surface(
        shape = MaterialTheme.shapes.extraSmall,
        color = MaterialTheme.colorScheme.inverseOnSurface,
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = "#${jobApiModel.jobNumber}",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray,
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
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
        }
    }
}