package com.example.reminder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reminder.databinding.FragmentMainBinding
import com.example.reminder.recyclerview.RecyclerViewAdapter
import com.example.reminder.viewModel.ApplicationClass
import com.example.reminder.viewModel.ReminderViewModel
import com.example.reminder.viewModel.ViewModelFactory

class MainFragment : Fragment() {

    private var _binding:FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReminderViewModel by activityViewModels{
        ViewModelFactory(
            (activity?.application as ApplicationClass).dataBase.memoDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moreBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_aboutFragment)
        }

        _binding?.addBtn?.setOnClickListener {

            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        val adapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.allMemoList.observe(this.viewLifecycleOwner, Observer { List->
            List?.let {
                adapter.updateAll(it)
            }
        })

    }

}