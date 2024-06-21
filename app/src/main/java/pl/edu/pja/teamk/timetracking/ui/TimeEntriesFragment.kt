package pl.edu.pja.teamk.timetracking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import pl.edu.pja.teamk.timetracking.R
import pl.edu.pja.teamk.timetracking.TimeEntry
import pl.edu.pja.teamk.timetracking.databinding.FragmentTimeEntriesBinding
import pl.edu.pja.teamk.timetracking.ui.home.HomeViewModel
import pl.edu.pja.teamk.timetracking.ui.home.areDateEqual

@AndroidEntryPoint
class TimeEntriesFragment : Fragment() {

    private lateinit var binding: FragmentTimeEntriesBinding
    private var columnCount = 1
    private val viewModel: HomeViewModel by activityViewModels<HomeViewModel>()

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
            val item = it.tag as TimeEntry
            val fragmentManager = parentFragmentManager
            fragmentManager.commit {
                replace(R.id.fragment_home_container, TimeEntryDetails())
                addToBackStack(null)
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