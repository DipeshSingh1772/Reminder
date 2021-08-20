package com.example.reminder

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.reminder.database.Memo
import com.example.reminder.databinding.FragmentAddBinding
import com.example.reminder.viewModel.ApplicationClass
import com.example.reminder.viewModel.ReminderViewModel
import com.example.reminder.viewModel.ViewModelFactory
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

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

        binding.saveBtn.setOnClickListener{
            insertMemoData()
        }
        binding.cancelBtn.setOnClickListener{
            findNavController().navigate(R.id.action_addFragment_to_mainFragment)
        }

        binding.time.setOnClickListener {

            if(binding.time.isChecked) {
                binding.time.isChecked = true
                openTimePicker()
            }
            else{
                binding.time.isChecked = false
                binding.timeBtn.text = "Time"
            }
        }

    }

    private fun insertMemoData(){
        val item:String = binding.reminderText.text.toString()
        if(item.isNotEmpty()) {
            viewModel.insertMemo(Memo(0,item,0))
            findNavController().navigate(R.id.action_addFragment_to_mainFragment)
        }
    }


    // Material Time Picker
    private fun openTimePicker(){
        val isSystem24hour = DateFormat.is24HourFormat(requireContext())
        val clockFormat = if(isSystem24hour) TimeFormat.CLOCK_12H else TimeFormat.CLOCK_12H

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(12)
            .setMinute(10)
            .setTitleText("Set Time")
            .build()
        picker.show(childFragmentManager,"Tag")

        picker.addOnPositiveButtonClickListener {
            var ampm:String

            if(picker.hour >12){
                ampm = "PM"
                binding.timeBtn.text = "${picker.hour - 12} : ${picker.minute} ${ampm}"
            }
            else if(picker.hour == 12){
                ampm = "PM"
                binding.timeBtn.text = "${picker.hour} : ${picker.minute} ${ampm}"
            }
            else{
                ampm = "AM"
                binding.timeBtn.text = "${picker.hour} : ${picker.minute} ${ampm}"
            }


        }
    }

}
