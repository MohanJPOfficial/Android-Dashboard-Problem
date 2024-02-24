package zuper.dev.android.dashboard.presentation.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import zuper.dev.android.dashboard.R
import zuper.dev.android.dashboard.presentation.dashboard.components.GreetingItem
import zuper.dev.android.dashboard.presentation.dashboard.components.InvoiceStatesItem
import zuper.dev.android.dashboard.presentation.dashboard.components.JobStatsItem
import zuper.dev.android.dashboard.presentation.navigation.Screen

@Composable
fun DashBoardScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            TopBar()

            Divider(modifier = Modifier
                .fillMaxWidth()
            )

            GreetingItem(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                todayDate = uiState.todayDate
            )

            JobStatsItem(
                modifier = modifier
                    .padding(10.dp)
                    .clickable {
                        navHostController.navigate(route = Screen.JOBS.routeName)
                    },
                jobStatsList = uiState.jobStatsList
            )

            InvoiceStatesItem(
                modifier = modifier
                    .padding(10.dp),
                invoiceStateList = uiState.invoiceStatsList
            )
        }
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(15.dp)
            .padding(top = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.dashboard),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}