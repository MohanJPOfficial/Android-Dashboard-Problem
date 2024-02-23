package zuper.dev.android.dashboard.presentation.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChartItem(
    modifier: Modifier = Modifier,
    color: Color = Color.Blue,
    status: String = "Completed",
    totalCount: String = "30"
) {
    Surface(
        modifier = modifier
            .padding(5.dp),
        color = Color.Transparent
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(3.dp))
                    .background(color)
            )

            Text(
                text = "$status ($totalCount)",
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Composable
@Preview
fun ChartItemPreview() {
    ChartItem()
}