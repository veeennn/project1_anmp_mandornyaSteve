package com.example.project1_anmp.view

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.project1_anmp.model.Habit
import com.example.project1_anmp.viewmodel.HabitViewModel
import com.example.project1_anmp.databinding.FragmentNewHabitBinding

class NewHabitFragment : Fragment() {
    private lateinit var binding: FragmentNewHabitBinding
    private lateinit var viewModel: HabitViewModel

    private val iconOptions = listOf("Water", "Exercise", "Book", "Meditation")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, iconOptions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIcon.adapter = spinnerAdapter

        binding.btnCreate.setOnClickListener {
            val name = binding.txtHabit.text.toString()
            val description = binding.txtDescription.text.toString()
            val goalStr = binding.txtGoal.text.toString()
            val unit = binding.txtUnit.text.toString()
            val iconName = binding.spinnerIcon.selectedItem.toString()

            if (name == "") {
                Toast.makeText(context, "Habit name is required!", Toast.LENGTH_SHORT).show()
            } else if (description == "") {
                Toast.makeText(context, "Description is required!", Toast.LENGTH_SHORT).show()
            } else if (goalStr == "") {
                Toast.makeText(context, "Goal is required!", Toast.LENGTH_SHORT).show()
            } else {
                val goal = Integer.parseInt(goalStr)
                if (goal <= 0) {
                    Toast.makeText(context, "Goal must be a positive number!", Toast.LENGTH_SHORT).show()
                } else {
                    val habit = Habit()
                    habit.name = name
                    habit.description = description
                    habit.goal = goal
                    habit.unit = unit
                    habit.iconName = iconName

                    viewModel.addHabit(habit)

                    Toast.makeText(context, "Habit created!", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(it).navigateUp()
                }
            }
        }
    }
}