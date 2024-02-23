package zuper.dev.android.dashboard.presentation.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import zuper.dev.android.dashboard.presentation.dashboard.components.GreetingItem
import zuper.dev.android.dashboard.presentation.dashboard.components.InvoiceStatesItem
import zuper.dev.android.dashboard.presentation.dashboard.components.JobStatsItem

@Composable
fun DashBoardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val jobStateList by viewModel.jobStateListFlow.collectAsState()
    val invoiceStateList by viewModel.invoiceStateListFlow.collectAsState()

    Surface(
        color = MaterialTheme.colorScheme.onSecondary,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            TopBar()
            GreetingItem(
                modifier = modifier
                    .padding(10.dp)
            )

            JobStatsItem(
                modifier = modifier
                    .padding(10.dp),
                jobStatsList = jobStateList
            )

            InvoiceStatesItem(
                modifier = modifier
                    .padding(10.dp),
                invoiceStateList = invoiceStateList
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
            .padding(top = 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Dashboard",
            modifier = Modifier
                .padding(10.dp),
            style = MaterialTheme.typography.titleMedium
        )

        Divider(modifier = Modifier
            .fillMaxWidth(),
            color = Color.Gray
        )
    }
}

@Preview
@Composable
fun DashBoardScreenPreview() {
    DashBoardScreen()
}