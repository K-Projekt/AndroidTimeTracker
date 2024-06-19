package pl.edu.pja.teamk.timetracking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.edu.pja.teamk.timetracking.R
import pl.edu.pja.teamk.timetracking.databinding.FragmentTimeEntriesBinding
import pl.edu.pja.teamk.timetracking.ui.home.HomeViewModel
import pl.edu.pja.teamk.timetracking.ui.home.areDateEqual

@AndroidEntryPoint
class TimeEntriesFragment : Fragment() {

    private lateinit var binding: FragmentTimeEntriesBinding
    private var columnCount = 1
    private val viewModel: HomeViewModel by viewModels()

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
        binding.list.adapter = MyTimeEntryRecyclerViewAdapter(viewModel.storeData.data.filter { areDateEqual(it.date, viewModel.selectedDate) }.flatMap { it.entries })
//        viewModel.listObservers.add {
//            binding.list.adapter = MyTimeEntryRecyclerViewAdapter(viewModel.storeData.data.filter { areDateEqual(it.date, viewModel.selectedDate) }.flatMap { it.entries })
//        }

    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TimeEntriesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}