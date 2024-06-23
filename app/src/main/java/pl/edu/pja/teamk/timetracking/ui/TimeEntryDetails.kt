package pl.edu.pja.teamk.timetracking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelStore
import dagger.hilt.android.AndroidEntryPoint
import pl.edu.pja.teamk.timetracking.R
import pl.edu.pja.teamk.timetracking.databinding.FragmentTimeEntryDetailsBinding
import pl.edu.pja.teamk.timetracking.ui.home.HomeFragment
import pl.edu.pja.teamk.timetracking.ui.home.HomeViewModel
import kotlin.time.Duration

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TimeEntryDetails.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class TimeEntryDetails : Fragment() {
    private val viewModelHome: HomeViewModel by activityViewModels<HomeViewModel>()
    private val viewTimeModel: TimeEntryDetailsViewModel by  activityViewModels<TimeEntryDetailsViewModel>()
    private lateinit var binding: FragmentTimeEntryDetailsBinding

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_time_entry_details, container, false)
        val root: View = binding.root

        val description = binding.TextInputDescription
        binding.buttonSave.setOnClickListener {
            val dur = Duration.parseIsoStringOrNull(TIMO_FORMAT_ISO_SUFFIX + binding.TextInputDate.text.toString())
            if (dur != null) {
                viewTimeModel.timeEntry.duration = dur
                viewTimeModel.timeEntry.description = description.text.toString()
                viewTimeModel.invoke()
            }
        }

        binding.buttonDelete.setOnClickListener {
            viewModelHome.storeData.data.remove(viewTimeModel.timeEntry)
            viewTimeModel.invoke()

            val fm = activity?.supportFragmentManager
            val frag = fm?.findFragmentByTag(FRAGMENT_NAME_TIME_ENTRY_DETAILS)
            if (frag != null) {
                val trans = fm.beginTransaction()
                trans.remove(frag)
                trans.commit()
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = binding.TextInputDate
        val description = binding.TextInputDescription
        val text = viewTimeModel.timeEntry.duration.toIsoString()
        date.setText(text.subSequence(INDEX_NEXT_AFTER_SUFFIX, text.length))
        description.setText(viewTimeModel.timeEntry.description)
    }

    companion object {
        const val TIMO_FORMAT_ISO_SUFFIX = "PT"
        const val FRAGMENT_NAME_TIME_ENTRY_DETAILS = "TimeEntryDetails"
        const val INDEX_NEXT_AFTER_SUFFIX = 2
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimeEntryDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}