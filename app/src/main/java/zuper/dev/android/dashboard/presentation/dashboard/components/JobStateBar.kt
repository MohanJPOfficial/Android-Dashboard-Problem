package zuper.dev.android.dashboard.presentation.dashboard.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import zuper.dev.android.dashboard.data.model.JobStatus
import zuper.dev.android.dashboard.domain.model.JobStatsModel
import zuper.dev.android.dashboard.presentation.dashboard.SampleData

@Composable
fun JobStateBar(
    modifier: Modifier = Modifier,
    list: List<JobStatsModel> = SampleData.list.sortedBy { it.totalSum }
) {
    if(list.isEmpty())
        return

    val totalJobs = list.sumOf { it.totalSum }

    val yetToStartWidthRatio = remember {
        Animatable(0f)
    }

    val inProgressWidthRatio = remember {
        Animatable(0f)
    }

    val cancelledWidthRatio = remember {
        Animatable(0f)
    }

    val completedWidthRatio = remember {
        Animatable(0f)
    }

    val incompleteWidthRatio = remember {
        Animatable(0f)
    }

    list.forEach { jobCategory ->
        when(jobCategory.jobStatus) {
            JobStatus.YetToStart -> {
                LaunchedEffect(key1 = jobCategory.totalSum) {
                    yetToStartWidthRatio.animateTo(
                        targetValue = ((jobCategory.totalSum.toFloat()) / totalJobs)
                    )
                }
            }
            JobStatus.InProgress -> {
                LaunchedEffect(key1 = jobCategory.totalSum) {
                    inProgressWidthRatio.animateTo(
                        targetValue = ((jobCategory.totalSum.toFloat()) / totalJobs)
                    )
                }
            }
            JobStatus.Canceled -> {
                LaunchedEffect(key1 = jobCategory.totalSum) {
                    cancelledWidthRatio.animateTo(
                        targetValue = ((jobCategory.totalSum.toFloat()) / totalJobs)
                    )
                }
            }
            JobStatus.Completed -> {
                LaunchedEffect(key1 = jobCategory.totalSum) {
                    completedWidthRatio.animateTo(
                        targetValue = ((jobCategory.totalSum.toFloat()) / totalJobs)
                    )
                }
            }
            JobStatus.Incomplete -> {
                LaunchedEffect(key1 = jobCategory.totalSum) {
                    incompleteWidthRatio.animateTo(
                        targetValue = ((jobCategory.totalSum.toFloat()) / totalJobs)
                    )
                }
            }
        }
    }

    Canvas(modifier = modifier) {

        val sortedList = listOf(
            yetToStartWidthRatio.value * size.width,
            inProgressWidthRatio.value * size.width,
            cancelledWidthRatio.value * size.width,
            completedWidthRatio.value * size.width,
            incompleteWidthRatio.value * size.width
        ).sorted()

        for(index in sortedList.indices) {
            drawRoundRect(
                color = list[index].color,
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
fun PreviewJobStateBar() {
    JobStateBar()
}