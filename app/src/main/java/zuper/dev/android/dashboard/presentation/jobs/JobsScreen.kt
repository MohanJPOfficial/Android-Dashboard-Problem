package zuper.dev.android.dashboard.presentation.jobs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import zuper.dev.android.dashboard.presentation.jobs.components.JobItemList
import zuper.dev.android.dashboard.presentation.jobs.components.JobStatsContainer
import zuper.dev.android.dashboard.presentation.jobs.components.TabRowItems
import zuper.dev.android.dashboard.presentation.jobs.components.TopBar

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

            JobStatsContainer(
                modifier = Modifier
                    .padding(10.dp),
                totalJobCount = uiState.totalJobCount,
                completedJobCount = uiState.completedJobCount,
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
            JobItemList(jobList = uiState.filteredJobList)
        }
    }
}