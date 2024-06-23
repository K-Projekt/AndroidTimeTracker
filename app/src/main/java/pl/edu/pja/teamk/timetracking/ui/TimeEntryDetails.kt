package pl.edu.pja.teamk.timetracking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import pl.edu.pja.teamk.timetracking.R
import pl.edu.pja.teamk.timetracking.TimeEntry
import pl.edu.pja.teamk.timetracking.databinding.FragmentTimeEntryDetailsBinding
import pl.edu.pja.teamk.timetracking.ui.home.HomeViewModel
import kotlin.time.Duration

@AndroidEntryPoint
class TimeEntryDetails : Fragment() {
    private val viewModelHome: HomeViewModel by activityViewModels<HomeViewModel>()
    private val viewTimeModel: TimeEntryDetailsViewModel by  activityViewModels<TimeEntryDetailsViewModel>()
    private lateinit var binding: FragmentTimeEntryDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_time_entry_details, container, false)
        val root: View = binding.root

        if (viewTimeModel.timeEntry != null) {
            binding.buttonDelete.visibility = View.VISIBLE
            val description = binding.TextInputDescription
            binding.buttonSave.setOnClickListener {
                val dur = Duration.parseIsoStringOrNull(TIMO_FORMAT_ISO_SUFFIX + binding.TextInputDate.text.toString())
                if (dur != null) {
                    viewTimeModel.timeEntry?.duration = dur
                    viewTimeModel.timeEntry?.description = description.text.toString()
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
        }
        else {
            binding.buttonDelete.visibility = View.INVISIBLE
            binding.buttonSave.setOnClickListener {
                val dur = Duration.parseIsoStringOrNull(TIMO_FORMAT_ISO_SUFFIX + binding.TextInputDate.text.toString())
                if (dur != null) {
                    val item = TimeEntry(viewModelHome.selectedDate,
                        dur, binding.TextInputDescription.text.toString(), TIME_ENTRY_DEFAULTS_CATEGORY)
                    viewModelHome.storeData.data.add(item)
                    viewTimeModel.invoke()
                }
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val date = binding.TextInputDate
        val description = binding.TextInputDescription
        val text = viewTimeModel.timeEntry?.duration?.toIsoString()
        date.setText(text?.subSequence(INDEX_NEXT_AFTER_SUFFIX, text.length))
        description.setText(viewTimeModel.timeEntry?.description)
    }

    companion object {
        const val TIMO_FORMAT_ISO_SUFFIX = "PT"
        const val FRAGMENT_NAME_TIME_ENTRY_DETAILS = "TimeEntryDetails"
        const val INDEX_NEXT_AFTER_SUFFIX = 2
        const val TIME_ENTRY_DEFAULTS_CATEGORY = 0
        const val TIME_ENTRY_DEFAULTS_DESCRIPTION = ""
    }
}