package zuper.dev.android.dashboard.data.model


/**
 * A simple API model representing a Job
 *
 * Start and end date time is in ISO 8601 format - Date and time are stored in UTC timezone and
 * expected to be shown on the UI in the local timezone
 */
data class JobApiModel(
    val jobNumber: Int,
    val title: String,
    val startTime: String,
    val endTime: String,
    val status: JobStatus
)

enum class JobStatus(val status: String) {
    YetToStart("Yet to start"),
    InProgress("In-progress"),
    Canceled("Cancelled"),
    Completed("Completed"),
    Incomplete("Incomplete")
}
