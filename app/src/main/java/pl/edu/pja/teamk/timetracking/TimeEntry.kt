package pl.edu.pja.teamk.timetracking
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.Duration
import java.util.Date
@Serializable
data class TimeEntry(
    @Contextual
    val start: Date,
    @Contextual
    val duration: Duration,
    val description: String,
    val category: Int
)

@Serializable
data class DayData(
    @Contextual
    val date: Date,
    val entries: List<TimeEntry>
)
