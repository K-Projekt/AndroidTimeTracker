package pl.edu.pja.teamk.timetracking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.findFragment
import dagger.hilt.android.AndroidEntryPoint
import pl.edu.pja.teamk.timetracking.R
import pl.edu.pja.teamk.timetracking.TimeEntry
import pl.edu.pja.teamk.timetracking.databinding.FragmentHomeBinding
import pl.edu.pja.teamk.timetracking.databinding.FragmentTimeEntriesBinding
import pl.edu.pja.teamk.timetracking.ui.home.HomeFragment
import pl.edu.pja.teamk.timetracking.ui.home.HomeViewModel
import pl.edu.pja.teamk.timetracking.ui.home.areDateEqual

@AndroidEntryPoint
class TimeEntriesFragment : Fragment() {

    private lateinit var binding: FragmentTimeEntriesBinding
    private var columnCount = 1
    private val viewModel: HomeViewModel by activityViewModels<HomeViewModel>()
    private val viewTimeModel: TimeEntryDetailsViewModel by activityViewModels<TimeEntryDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

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
        binding.list.adapter = MyTimeEntryRecyclerViewAdapter(mutableListOf())
        val adapter = binding.list.adapter as MyTimeEntryRecyclerViewAdapter
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

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            TimeEntriesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}