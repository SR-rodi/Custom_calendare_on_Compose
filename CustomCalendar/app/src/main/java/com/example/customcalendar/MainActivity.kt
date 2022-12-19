package com.example.customcalendar

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.customcalendar.customCalendar.data.CalendarBuilder
import com.example.customcalendar.customCalendar.presentation.CustomCalendar

import com.example.customcalendar.customCalendar.presentation.calendarState
import java.text.SimpleDateFormat


class MainActivity : ComponentActivity() {
    @SuppressLint("SimpleDateFormat")
    @Suppress("OPT_IN_IS_NOT_ENABLED")
    private val sdf = SimpleDateFormat("dd-MM-yyyy")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val calendarState = calendarState()
            CustomCalendar(
                modifier= Modifier.padding(horizontal = 16.dp, vertical = 25.dp),
                calendarState = calendarState,
                onClickMonth = {
                    calendarState.value = CalendarBuilder.createCalendar()
                }) { calendar ->
                Toast.makeText(applicationContext,
                    sdf.format(calendar.timeInMillis),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}