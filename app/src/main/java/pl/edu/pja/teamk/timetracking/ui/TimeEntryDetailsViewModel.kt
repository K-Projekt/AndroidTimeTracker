package pl.edu.pja.teamk.timetracking.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.edu.pja.teamk.timetracking.TimeEntry
import javax.inject.Inject

@HiltViewModel
class TimeEntryDetailsViewModel @Inject constructor() : ViewModel() {
    lateinit var timeEntry: TimeEntry
    val listObservers = mutableListOf<() -> Unit>()

    fun invoke() {
        listObservers.forEach { it.invoke() }
    }
}
