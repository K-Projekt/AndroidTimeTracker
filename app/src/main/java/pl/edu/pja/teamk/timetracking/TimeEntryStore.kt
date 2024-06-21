package pl.edu.pja.teamk.timetracking

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import java.io.File
import java.util.Date
import kotlin.time.Duration

class TimeEntryStore : TimeSource {
    var data: MutableList<TimeEntry> = mutableListOf()

    override fun loadData(context: Context) {
        if (!File(context.filesDir, FILE_DATA).exists()) {
            data = getMockData()
            Log.w("TimeEntryStore", "File $FILE_DATA has not been found - populating with mock data")
            return
        }

        val file = File(context.filesDir, FILE_DATA)
        val text = file.readText()

        try {
            Gson().fromJson(text, Array<TimeEntry>::class.java).toMutableList()
        } catch (e: Exception) {
            Log.e("TimeEntryStore", "Error while reading data from file $FILE_DATA: ${e.message}")
            getMockData()
        }.also {
            data = it
            Log.i("TimeEntryStore", "Data loaded from file $FILE_DATA")
        }
    }

    override fun saveData(context: Context, data: MutableList<TimeEntry>) {
        try {
            val dataJson = Gson().toJson(data)
            val file = File(context.filesDir, FILE_DATA)
            file.writeText(dataJson)
            Log.i("TimeEntryStore", "Data saved to file $FILE_DATA")
        } catch (e: Exception) {
            Log.e("TimeEntryStore", "Error while saving data to file $FILE_DATA: ${e.message}")
        }
    }

    companion object {
        const val FILE_DATA = "data.json"

        fun getMockData(): MutableList<TimeEntry>{
            return mutableListOf(
                TimeEntry(Date(), Duration.parse("PT2H"), "test", 0),
                TimeEntry(Date(), Duration.parse("PT3H"), "test", 0),
                TimeEntry(Date(), Duration.parse("PT2H"), "test", 0),
                TimeEntry(Date(), Duration.parse("PT1H30M"), "test", 0),
                TimeEntry(Date(), Duration.parse("PT2H45M"), "test", 0),
                TimeEntry(Date(), Duration.parse("PT3H30M"), "test", 1),
                TimeEntry(Date(), Duration.parse("PT30M"), "test", 1),
                TimeEntry(Date(), Duration.parse("PT1H30M"), "test", 0)
            )
        }
    }
}

interface TimeSource {
    fun loadData(context: Context)
    fun saveData(context: Context, data: MutableList<TimeEntry>)
}
