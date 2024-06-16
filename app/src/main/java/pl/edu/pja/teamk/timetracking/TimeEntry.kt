package pl.edu.pja.teamk.timetracking

import java.time.Duration
import java.util.Date

data class TimeEntry(
    val start: Date,
    val duration: Duration,
    val description: String,
    val category: Int
)

data class DayEntry(
    val date: Date,
    val entries: List<TimeEntry>
)
