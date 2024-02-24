package zuper.dev.android.dashboard.presentation.dashboard.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import zuper.dev.android.dashboard.data.model.InvoiceStatus
import zuper.dev.android.dashboard.domain.model.InvoiceStatsModel

@Composable
fun InvoiceStateBar(
    modifier: Modifier = Modifier,
    invoiceStateList: List<InvoiceStatsModel>
) {
    if(invoiceStateList.isEmpty())
        return

    val totalAmount = invoiceStateList.sumOf { it.totalSum }

    /**
     *  Draft,
     *  Pending,
     *  Paid,
     *  BadDebt
     */
    val draftWidthRatio = remember {
        Animatable(0f)
    }

    val pendingWidthRatio = remember {
        Animatable(0f)
    }

    val paidWidthRatio = remember {
        Animatable(0f)
    }

    val badDebtWidthRatio = remember {
        Animatable(0f)
    }

    invoiceStateList.forEach { invoiceState ->
        when(invoiceState.invoiceStatus) {
            InvoiceStatus.Draft -> {
                LaunchedEffect(key1 = invoiceState.totalSum) {
                    draftWidthRatio.animateTo(
                        targetValue = ((invoiceState.totalSum.toFloat()) / totalAmount)
                    )
                }
            }
            InvoiceStatus.Pending -> {
                LaunchedEffect(key1 = invoiceState.totalSum) {
                    pendingWidthRatio.animateTo(
                        targetValue = ((invoiceState.totalSum.toFloat()) / totalAmount)
                    )
                }
            }
            InvoiceStatus.Paid -> {
                LaunchedEffect(key1 = invoiceState.totalSum) {
                    paidWidthRatio.animateTo(
                        targetValue = ((invoiceState.totalSum.toFloat()) / totalAmount)
                    )
                }
            }
            InvoiceStatus.BadDebt -> {
                LaunchedEffect(key1 = invoiceState.totalSum) {
                    badDebtWidthRatio.animateTo(
                        targetValue = ((invoiceState.totalSum.toFloat()) / totalAmount)
                    )
                }
            }
        }
    }

    Canvas(modifier = modifier) {

        val sortedList = listOf(
            draftWidthRatio.value * size.width,
            pendingWidthRatio.value * size.width,
            paidWidthRatio.value * size.width,
            badDebtWidthRatio.value * size.width,
        ).sorted()

        for(index in sortedList.indices) {
            drawRoundRect(
                color = invoiceStateList[index].color,
                size = Size(
                    width = sortedList.subList(index, sortedList.size).sum(),
                    height = size.height
                ),
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PreviewInvoiceStateBar() {
    InvoiceStateBar(
        invoiceStateList = emptyList()
    )
}