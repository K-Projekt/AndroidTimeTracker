package pl.edu.pja.teamk.timetracking

import android.content.Context
import com.google.gson.Gson
import java.io.File
import java.util.Date
import kotlin.time.Duration

class TimeEntryStore() : TimeSource {

    var data: MutableList<DayData> = mutableListOf()

    override fun loadData(context: Context) {

        // TODO: Damian Kreft - Replace with actual data.
        if (!File(context.filesDir, filename).exists()) {
            data = getMockData()
            return
        }

        val file = File(context.filesDir, filename)
        val text = file.readText()

        // TODO: Handle exceptions.
        data = Gson().fromJson(text, Array<DayData>::class.java).toMutableList()
    }

    override fun saveData(context: Context, data: MutableList<DayData>) {
        val dataJson = Gson().toJson(data)
        val file = File(context.filesDir, filename)
        file.writeText(dataJson)

        // TODO: Handle exceptions.
    }

    companion object {
        const val filename = "data.json"

        fun getMockData(): MutableList<DayData>{
            return mutableListOf(
                DayData(Date(), mutableListOf(
                    TimeEntry(Date(), Duration.parse("PT2H"), "test", 0),
                    TimeEntry(Date(), Duration.parse("PT3H"), "test", 0),
                    TimeEntry(Date(), Duration.parse("PT2H"), "test", 0),
                    TimeEntry(Date(), Duration.parse("PT1H30M"), "test", 0),
                )),
                DayData(Date(), mutableListOf(
                    TimeEntry(Date(), Duration.parse("PT2H45M"), "test", 0),
                    TimeEntry(Date(), Duration.parse("PT3H30M"), "test", 1),
                    TimeEntry(Date(), Duration.parse("PT30M"), "test", 1),
                    TimeEntry(Date(), Duration.parse("PT1H30M"), "test", 0),
                )))

        }
    }
}

interface TimeSource {
    fun loadData(context: Context)
    fun saveData(context: Context, data: MutableList<DayData>)
}
