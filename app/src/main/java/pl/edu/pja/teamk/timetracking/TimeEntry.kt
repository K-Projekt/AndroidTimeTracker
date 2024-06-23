package pl.edu.pja.teamk.timetracking
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date
import kotlin.time.Duration

@Serializable
data class TimeEntry(
    @Contextual
    var start: Date,
    @Contextual
    var duration: Duration,
    var description: String,
    var category: Int
)