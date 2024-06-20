package pl.edu.pja.teamk.timetracking
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date
import kotlin.time.Duration

@Serializable
data class TimeEntry(
    @Contextual
    val start: Date,
    @Contextual
    val duration: Duration,
    val description: String,
    val category: Int
)