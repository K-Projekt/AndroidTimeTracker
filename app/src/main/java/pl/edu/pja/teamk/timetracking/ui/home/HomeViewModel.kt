package pl.edu.pja.teamk.timetracking.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.edu.pja.teamk.timetracking.TimeEntryStore
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(handle: SavedStateHandle, private val store: TimeEntryStore) : ViewModel() {

    private val _testText = MutableLiveData<String>().apply {
        value = store.testFun()
    }
    var testText: MutableLiveData<String> = _testText
}


//@HiltViewModel
//class DonutViewModel @Inject constructor(handle: SavedStateHandle?, repository: RecipeRepository?) :
//    ViewModel()