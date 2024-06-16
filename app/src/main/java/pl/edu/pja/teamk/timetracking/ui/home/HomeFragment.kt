package pl.edu.pja.teamk.timetracking.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import pl.edu.pja.teamk.timetracking.databinding.FragmentHomeBinding
import java.util.Calendar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
//        _viewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val calendar: CalendarView = binding.calendarView2

        textView.text = viewModel.testText.value
//        calendar.post {
//            val date = Calendar.getInstance()
//            date.timeInMillis = calendar.date
//            textView.text = date.get(Calendar.DAY_OF_MONTH).toString()
//
//        }

        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
//            textView.text = dayOfMonth.toString()
//            textView.text = viewModel.testText.value
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}