package pl.edu.pja.teamk.timetracking.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.edu.pja.teamk.timetracking.TimeEntry
import pl.edu.pja.teamk.timetracking.TimeEntryStore
import javax.inject.Inject

@HiltViewModel
class TimeEntryDetailsViewModel @Inject constructor(handle: SavedStateHandle) : ViewModel() {
    lateinit var timeEntry: TimeEntry
}
