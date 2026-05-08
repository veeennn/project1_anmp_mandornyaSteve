package com.example.project1_anmp.model

data class Habit(
    var id: Int = 0,
    var name: String = "",
    var description: String = "",
    var goal: Int = 1,
    var progress: Int = 0,
    var unit: String = "",
    var iconName: String = "Star"
)
