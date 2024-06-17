package pl.edu.pja.teamk.timetracking.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import hilt_aggregated_deps._dagger_hilt_android_internal_modules_ApplicationContextModule
import pl.edu.pja.teamk.timetracking.R
import pl.edu.pja.teamk.timetracking.databinding.FragmentNotificationsBinding

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val notificationsViewModel =
//            ViewModelProvider(this).get(NotificationsViewModel::class.java)
//        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_notifications, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}