package pl.edu.pja.teamk.timetracking.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import pl.edu.pja.teamk.timetracking.R
import pl.edu.pja.teamk.timetracking.databinding.FragmentHomeBinding
import pl.edu.pja.teamk.timetracking.ui.TimeEntryDetails
import java.util.Calendar

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val root: View = binding.root

        val calendar: CalendarView = binding.calendarView2

        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(Calendar.YEAR, year)
            selectedDate.set(Calendar.MONTH, month)
            selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            viewModel.setSelectedDate(selectedDate.timeInMillis)
        }

        val fab: View = binding.fab
        fab.setOnClickListener { view ->
            val fm = activity?.supportFragmentManager
                    if (fm != null && fm.findFragmentByTag("AddTimeEntryDetails") == null) {
                        val trans = fm.beginTransaction()
                        trans.add(R.id.fragment_home_container, TimeEntryDetails(), "AddTimeEntryDetails")
                        trans.addToBackStack(null)
                        trans.commit()
                    }
        }

        calendar.post { calendar.date = viewModel.selectedDate.time }
        return root
    }
}