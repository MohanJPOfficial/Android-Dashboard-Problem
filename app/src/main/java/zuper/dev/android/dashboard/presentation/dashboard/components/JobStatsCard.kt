package zuper.dev.android.dashboard.presentation.dashboard.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.R
import zuper.dev.android.dashboard.domain.model.JobStatsModel

@Composable
fun JobStatsItem(
    modifier: Modifier = Modifier,
    jobStatsList: List<JobStatsModel>,
    totalJobCount: Int,
    completedJobCount: Int
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(4.dp))
            .border(
                border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant),
                shape = RoundedCornerShape(4.dp),
            )
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = stringResource(R.string.job_stats),
            modifier = Modifier
                .padding(start = 10.dp, bottom = 10.dp),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Divider(modifier = Modifier
            .fillMaxWidth()
        )

        if(jobStatsList.isEmpty())
            return@Column

        JobStatsContainer(
            totalJobCount = totalJobCount,
            completedJobCount = completedJobCount,
            jobStatsList = jobStatsList
        )

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ChartItem(
                    color = jobStatsList[0].color,
                    status = jobStatsList[0].jobStatus.status,
                    totalCount = jobStatsList[0].totalSum.toString()
                )
                ChartItem(
                    color = jobStatsList[1].color,
                    status = jobStatsList[1].jobStatus.status,
                    totalCount = jobStatsList[1].totalSum.toString()
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ChartItem(
                    color = jobStatsList[2].color,
                    status = jobStatsList[2].jobStatus.status,
                    totalCount = jobStatsList[2].totalSum.toString()
                )
                ChartItem(
                    color = jobStatsList[3].color,
                    status = jobStatsList[3].jobStatus.status,
                    totalCount = jobStatsList[3].totalSum.toString()
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ChartItem(
                    color = jobStatsList[4].color,
                    status = jobStatsList[4].jobStatus.status,
                    totalCount = jobStatsList[4].totalSum.toString()
                )
            }
        }
    }
}