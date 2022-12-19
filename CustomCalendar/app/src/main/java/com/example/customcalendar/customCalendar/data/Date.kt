package com.example.customcalendar.customCalendar.data

data class Date(
    val day: Int,
    var isSelected: Boolean = false,
    var isWeekend: Boolean = false,
    var isPastMonth: Boolean = false,
)