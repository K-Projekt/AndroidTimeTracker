package pl.edu.pja.teamk.timetracking.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.edu.pja.teamk.timetracking.TimeEntryStore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(handle: SavedStateHandle, private val store: TimeEntryStore) : ViewModel() {
    var selectedDate: Date = Date()
    val storeData: TimeEntryStore = store
    fun setSelectedDate(ticks: Long) {
        val date = Calendar.getInstance()
        date.timeInMillis = ticks
        selectedDate.time = ticks
    }

    private val _testText = MutableLiveData<String>().apply {
        value = store.testFun()
    }
    var testText: MutableLiveData<String> = _testText
}

fun areDateEqual(date1: Date, date2: Date): Boolean {
    val c1 = Calendar.getInstance()
    c1.timeInMillis = date1.time
    val c2 = Calendar.getInstance()
    c2.timeInMillis = date2.time
    val format = SimpleDateFormat("yyyy-MM-dd")
    val d1 = format.format(c1.time)
    val d2 = format.format(c2.time)
    return d1.equals(d2)
}
