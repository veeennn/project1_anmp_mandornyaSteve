package com.example.project1_anmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project1_anmp.model.Habit
import com.example.project1_anmp.R
import com.example.project1_anmp.viewmodel.HabitViewModel
import com.example.project1_anmp.databinding.HabitCardBinding

class HabitListAdapter(
    val habitList: ArrayList<Habit>,
    private val viewModel: HabitViewModel
) : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    class HabitViewHolder(var binding: HabitCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = HabitCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]

        holder.binding.txtName.text = habit.name
        holder.binding.txtDesc.text = habit.description

        if (habit.unit == "") {
            holder.binding.txtProgress.text = habit.progress.toString() + " / " + habit.goal.toString()
        } else {
            holder.binding.txtProgress.text = habit.progress.toString() + " / " + habit.goal.toString() + " " + habit.unit
        }

        holder.binding.progressBar.max = habit.goal
        holder.binding.progressBar.progress = habit.progress

        if (habit.progress >= habit.goal) {
            holder.binding.txtStatus.text = "Completed"
            holder.binding.btnPlus.isEnabled = false
        } else {
            holder.binding.txtStatus.text = "In Progress"
            holder.binding.btnPlus.isEnabled = true
        }

        if (habit.progress > 0) {
            holder.binding.btnMinus.isEnabled = true
        } else {
            holder.binding.btnMinus.isEnabled = false
        }

        val iconRes = getIconRes(habit.iconName)
        holder.binding.imageView3.setImageResource(iconRes)

        holder.binding.btnPlus.setOnClickListener {
            viewModel.incrementProgress(habit.id)
        }
        
        holder.binding.btnMinus.setOnClickListener {
            viewModel.decrementProgress(habit.id)
        }
    }

    override fun getItemCount(): Int = habitList.size

    fun updateHabitList(newHabitList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }

    private fun getIconRes(iconName: String): Int {
        if (iconName == "Water") {
            return R.drawable.baseline_water_drop_24
        } else if (iconName == "Exercise") {
            return R.drawable.baseline_handshake_24
        } else if (iconName == "Book") {
            return R.drawable.baseline_book_24
        } else if (iconName == "Meditation") {
            return R.drawable.baseline_nature_people_24
        } else {
            return R.drawable.ic_launcher_foreground
        }
    }
}
