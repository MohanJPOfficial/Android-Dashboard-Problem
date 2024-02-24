package zuper.dev.android.dashboard.presentation.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.R
import zuper.dev.android.dashboard.domain.model.JobStatsModel
import zuper.dev.android.dashboard.presentation.dashboard.components.JobStatsBar

@Composable
fun JobStatsContainer(
    modifier: Modifier = Modifier,
    totalJobCount: Int,
    completedJobCount: Int,
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
                text = stringResource(R.string.total_jobs, totalJobCount),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(
                    R.string.completed_jobs,
                    completedJobCount,
                    totalJobCount
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
                fontWeight = FontWeight.Bold
            )
        }

        if(jobStatsList.isEmpty())
            return@Column

        JobStatsBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(25.dp)
                .clip(RoundedCornerShape(5.dp)),
            jobStatsList = jobStatsList
        )
    }
}

