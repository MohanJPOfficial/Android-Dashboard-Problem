package zuper.dev.android.dashboard.presentation.jobs.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.R
import zuper.dev.android.dashboard.data.model.JobStatus
import zuper.dev.android.dashboard.domain.model.JobStatsModel
import zuper.dev.android.dashboard.presentation.dashboard.SampleData
import zuper.dev.android.dashboard.presentation.dashboard.components.JobStateBar

@Composable
fun JobStatsItem(
    modifier: Modifier = Modifier,
    jobStatsList: List<JobStatsModel>
) {
    Column(
        modifier = modifier
            .padding(vertical = 15.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.total_jobs, jobStatsList.sumOf { it.totalSum }),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(
                    R.string.completed_jobs,
                    jobStatsList.find { it.jobStatus == JobStatus.Completed }?.totalSum ?: 0,
                    jobStatsList.sumOf { it.totalSum }
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
                fontWeight = FontWeight.Bold
            )
        }

        if(jobStatsList.isEmpty())
            return@Column

        JobStateBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(25.dp)
                .clip(RoundedCornerShape(5.dp)),
            list = jobStatsList
        )
    }
}

