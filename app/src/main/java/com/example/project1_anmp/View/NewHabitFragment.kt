package com.example.project1_anmp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project1_anmp.R
import com.example.project1_anmp.databinding.FragmentNewHabitBinding


class NewHabitFragment : Fragment() {
    private lateinit var binding: FragmentNewHabitBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewHabitBinding.inflate(inflater)
        return binding.root
    }
}