package zuper.dev.android.dashboard.presentation.jobs.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.data.model.JobApiModel
import zuper.dev.android.dashboard.presentation.util.DateUtils
import zuper.dev.android.dashboard.presentation.util.withHashTag

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