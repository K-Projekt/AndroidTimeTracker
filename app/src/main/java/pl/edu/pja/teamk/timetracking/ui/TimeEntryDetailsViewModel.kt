package pl.edu.pja.teamk.timetracking.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.edu.pja.teamk.timetracking.TimeEntry
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class TimeEntryDetailsViewModel @Inject constructor() : ViewModel() {
    lateinit var timeEntry: TimeEntry
    val listObservers = mutableListOf<() -> Unit>()
//    var invoke: Unit by Delegates.observable(Unit) { _, _, _ ->
//        listObservers.forEach { it.invoke() }}

    fun invoke() {
        listObservers.forEach { it.invoke() }
    }
}
