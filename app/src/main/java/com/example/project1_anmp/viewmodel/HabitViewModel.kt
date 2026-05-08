package com.example.project1_anmp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.project1_anmp.model.Habit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()

    private val sharedPreferences = application.getSharedPreferences("habit", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun refresh() {
        val json = sharedPreferences.getString("habits", null)
        if (json != null) {
            val type = object : TypeToken<ArrayList<Habit>>() {}.type
            habitsLD.value = gson.fromJson(json, type)
        } else {
            habitsLD.value = arrayListOf()
        }
    }

    private fun saveHabits(habits: ArrayList<Habit>) {
        val json = gson.toJson(habits)
        sharedPreferences.edit().putString("habits", json).apply()
    }

    private fun getNextId(): Int {
        val habits = habitsLD.value
        if (habits == null || habits.isEmpty()) {
            return 1
        }
        return habits.size + 1
    }

    fun addHabit(habit: Habit) {
        refresh()
        var habits = habitsLD.value

        if (habits == null) {
            habits = arrayListOf()
        }
        
        habit.id = getNextId()
        habits.add(habit)
        
        saveHabits(habits)
        refresh()
    }

    fun incrementProgress(habitId: Int) {
        val habits = habitsLD.value
        if (habits != null) {
            for (habit in habits) {
                if (habit.id == habitId) {
                    if (habit.progress < habit.goal) {
                        habit.progress += 1
                        saveHabits(habits)
                        refresh()
                    }
                    break
                }
            }
        }
    }

    fun decrementProgress(habitId: Int) {
        val habits = habitsLD.value
        if (habits != null) {
            for (habit in habits) {
                if (habit.id == habitId) {
                    if (habit.progress > 0) {
                        habit.progress -= 1
                        saveHabits(habits)
                        refresh()
                    }
                    break
                }
            }
        }
    }
}
