package pl.edu.pja.teamk.timetracking

import android.content.Context
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.Date

class TimeEntryStore() : TimeSource {
    init { }
    var data: MutableList<DayData> = mutableListOf()

    fun testFun(): String {
        return "Hilt works!"
    }

    override fun loadData(context: Context) {
        val json = Json { ignoreUnknownKeys = true }
        val filename = "appdata"
        val file = File(context.filesDir, filename)
        val text = file.readText()
        // handle exceptions

        data = json.decodeFromString<MutableList<DayData>>(text)
    }

    override fun saveData(context: Context, data: MutableList<DayData>) {
        val json = Json { ignoreUnknownKeys = true }
        val dataJson = json.encodeToString(data)
        val filename = "appdata"
        val file = File(context.filesDir, filename)
        file.writeText(dataJson)

        // handle exceptions
    }
}

interface TimeSource {
    fun loadData(context: Context)
    fun saveData(context: Context, data: MutableList<DayData>)
}
