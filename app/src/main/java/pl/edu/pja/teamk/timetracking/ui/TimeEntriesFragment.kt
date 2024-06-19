package pl.edu.pja.teamk.timetracking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.edu.pja.teamk.timetracking.R
import pl.edu.pja.teamk.timetracking.TimeEntry
import pl.edu.pja.teamk.timetracking.TimeEntryStore
import pl.edu.pja.teamk.timetracking.databinding.FragmentTimeEntriesBinding
import pl.edu.pja.teamk.timetracking.ui.home.HomeViewModel
import java.util.Date
import javax.inject.Inject
import kotlin.time.Duration

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class TimeEntriesFragment : Fragment() {

//    lateinit var store: TimeEntryStore
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
//        try {
            val view = inflater.inflate(R.layout.fragment_time_entries, container, false)
            if (view is RecyclerView) {
                (view as RecyclerView).adapter = MyTimeEntryRecyclerViewAdapter(mutableListOf(
                    TimeEntry(Date(), Duration.parse("PT2H"), "test", 0),
                    TimeEntry(Date(), Duration.parse("PT3H"), "test", 0),
                    TimeEntry(Date(), Duration.parse("PT2H"), "test", 0),
                    TimeEntry(Date(), Duration.parse("PT1H30M"), "test", 0),
                ))
            }
//            else {
//                binding = FragmentTimeEntriesBinding.inflate(layoutInflater, container, false)
//            }
//            binding.list.adapter = MyTimeEntryRecyclerViewAdapter(mutableListOf(
//                TimeEntry(Date(), Duration.parse("PT2H"), "test", 0),
//                TimeEntry(Date(), Duration.parse("PT3H"), "test", 0),
//                TimeEntry(Date(), Duration.parse("PT2H"), "test", 0),
//                TimeEntry(Date(), Duration.parse("PT1H30M"), "test", 0),
//            ))
//            binding.list.adapter = MyTimeEntryRecyclerViewAdapter(store.data.flatMap { it.entries })
//        }
//        catch (e: Exception) {
//            e.printStackTrace()
//        }
        return view
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