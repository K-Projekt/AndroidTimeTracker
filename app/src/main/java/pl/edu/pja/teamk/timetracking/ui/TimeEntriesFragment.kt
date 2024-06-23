package pl.edu.pja.teamk.timetracking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.edu.pja.teamk.timetracking.R
import pl.edu.pja.teamk.timetracking.TimeEntry
import pl.edu.pja.teamk.timetracking.databinding.FragmentTimeEntriesBinding
import pl.edu.pja.teamk.timetracking.ui.home.HomeViewModel
import pl.edu.pja.teamk.timetracking.ui.home.areDateEqual

@AndroidEntryPoint
class TimeEntriesFragment : Fragment() {

    private lateinit var binding: FragmentTimeEntriesBinding
    private val viewModel: HomeViewModel by activityViewModels<HomeViewModel>()
    private val viewTimeModel: TimeEntryDetailsViewModel by activityViewModels<TimeEntryDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            val view = inflater.inflate(R.layout.fragment_time_entries, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTimeEntriesBinding.bind(view)
        val adapter = MyTimeEntryRecyclerViewAdapter(mutableListOf())
        binding.list.adapter = adapter
        viewTimeModel.listObservers.add {
            adapter.setData(viewModel.storeData.data.filter { areDateEqual(it.start, viewModel.selectedDate) }.toMutableList())
        }
        adapter.onClickListener = View.OnClickListener {
            viewTimeModel.timeEntry = it.tag as TimeEntry

            val fm = activity?.supportFragmentManager
            if (fm != null && fm.findFragmentByTag("TimeEntryDetails") == null) {
                val trans = fm.beginTransaction()
                trans.add(R.id.fragment_home_container, TimeEntryDetails(), "TimeEntryDetails")
                trans.addToBackStack(null)
                trans.commit()
            }
        }
        adapter.setData(viewModel.storeData.data.filter { areDateEqual(it.start, viewModel.selectedDate) }.toMutableList())
        viewModel.listObservers.add {selected ->
            val data = viewModel.storeData.data.filter { areDateEqual(it.start, selected) }.toMutableList()
            adapter.setData(data)
        }
    }
}