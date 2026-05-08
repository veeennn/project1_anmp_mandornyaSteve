package com.example.project1_anmp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1_anmp.viewmodel.HabitViewModel
import com.example.project1_anmp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: HabitViewModel
    private lateinit var habitListAdapter: HabitListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
        viewModel.refresh()

        habitListAdapter = HabitListAdapter(arrayListOf(), viewModel)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = habitListAdapter

        binding.fabAdd.setOnClickListener {
            val action = DashboardFragmentDirections.actionNewHabitFragment()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel.habitsLD.observe(viewLifecycleOwner, Observer {
            habitListAdapter.updateHabitList(it)
        })
    }
}