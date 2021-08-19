package com.example.reminder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.reminder.databinding.FragmentAddBinding
import com.example.reminder.viewModel.ApplicationClass
import com.example.reminder.viewModel.ReminderViewModel
import com.example.reminder.viewModel.ViewModelFactory

class AddFragment : Fragment() {

    private var _binding:FragmentAddBinding? = null
    private val binding get() = _binding!!


    private val viewModel:ReminderViewModel by activityViewModels{
        ViewModelFactory(
            (activity?.application as ApplicationClass).dataBase.memoDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}
