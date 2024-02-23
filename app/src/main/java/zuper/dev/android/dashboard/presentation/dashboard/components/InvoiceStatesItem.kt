package zuper.dev.android.dashboard.presentation.dashboard.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.data.model.InvoiceStatus
import zuper.dev.android.dashboard.domain.model.InvoiceStatsModel

@Composable
fun InvoiceStatesItem(
    modifier: Modifier = Modifier,
    invoiceStateList: List<InvoiceStatsModel>
) {
    Surface(
        shape = MaterialTheme.shapes.extraSmall,
        color = MaterialTheme.colorScheme.inverseOnSurface,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp)
        ) {
            Text(
                text = "Invoice States",
                modifier = Modifier
                    .padding(start = 10.dp, bottom = 10.dp),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.width(4.dp))

            Divider(modifier = Modifier
                .fillMaxWidth(),
                color = Color.Gray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total value ($${invoiceStateList.sumOf { it.totalSum }})",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Text(
                    text = "$${invoiceStateList.find { it.invoiceStatus == InvoiceStatus.Paid }?.totalSum} collected",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            if(invoiceStateList.isEmpty())
                return@Column

            InvoiceStateBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(25.dp)
                    .clip(RoundedCornerShape(5.dp)),
                invoiceStateList = invoiceStateList
            )

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ChartItem(
                        color = invoiceStateList[0].color,
                        status = invoiceStateList[0].invoiceStatus.name,
                        totalCount = "$${invoiceStateList[0].totalSum}"
                    )
                    ChartItem(
                        color = invoiceStateList[1].color,
                        status = invoiceStateList[1].invoiceStatus.name,
                        totalCount = "$${invoiceStateList[1].totalSum}"
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ChartItem(
                        color = invoiceStateList[2].color,
                        status = invoiceStateList[2].invoiceStatus.name,
                        totalCount = "$${invoiceStateList[2].totalSum}"
                    )
                    ChartItem(
                        color = invoiceStateList[3].color,
                        status = invoiceStateList[3].invoiceStatus.name,
                        totalCount = "$${invoiceStateList[3].totalSum}"
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun InvoiceStatesItemPreview() {
    InvoiceStatesItem(invoiceStateList = emptyList())
}

