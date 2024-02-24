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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.R
import zuper.dev.android.dashboard.domain.model.InvoiceStatsModel
import zuper.dev.android.dashboard.presentation.util.withComma

@Composable
fun InvoiceStatesItem(
    modifier: Modifier = Modifier,
    invoiceStateList: List<InvoiceStatsModel>,
    totalInvoiceValue: Int,
    collectedInvoiceValue: Int
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
            text = stringResource(R.string.invoice_stats),
            modifier = Modifier
                .padding(start = 10.dp, bottom = 10.dp),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Divider(modifier = Modifier
            .fillMaxWidth(),
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
                text = stringResource(
                    R.string.total_value,
                    totalInvoiceValue.withComma()
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(
                    R.string.collected_value,
                    collectedInvoiceValue.withComma()
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
                fontWeight = FontWeight.Bold
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

