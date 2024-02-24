package zuper.dev.android.dashboard.presentation.dashboard.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import zuper.dev.android.dashboard.data.model.JobStatus
import zuper.dev.android.dashboard.domain.model.JobStatsModel

@Composable
fun JobStatsBar(
    modifier: Modifier = Modifier,
    jobStatsList: List<JobStatsModel>
) {
    if(jobStatsList.isEmpty())
        return

    val totalJobs = jobStatsList.sumOf { it.totalSum }

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

    jobStatsList.forEach { jobCategory ->
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
                color = jobStatsList[index].color,
                size = Size(
                    width = sortedList.subList(index, sortedList.size).sum(),
                    height = size.height
                ),
            )
        }
    }
}