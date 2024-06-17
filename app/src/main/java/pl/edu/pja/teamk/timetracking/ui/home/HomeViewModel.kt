package pl.edu.pja.teamk.timetracking.ui.home

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.edu.pja.teamk.timetracking.TimeEntryStore
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(handle: SavedStateHandle, private val store: TimeEntryStore) : ViewModel() {
    var selectedDate: Date = Date()
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


//@HiltViewModel
//class DonutViewModel @Inject constructor(handle: SavedStateHandle?, repository: RecipeRepository?) :
//    ViewModel()