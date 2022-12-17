package com.example.customcalendar

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.customcalendar.customCalendar.viewmodel.CalendarViewModel
import com.example.customcalendar.customCalendar.CustomCalendar
import java.text.SimpleDateFormat

@Suppress("OPT_IN_IS_NOT_ENABLED")
class MainActivity : ComponentActivity() {
    private val viewModel = CalendarViewModel()

    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("dd-MM-yyyy")

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CustomCalendar(
                viewModel = viewModel,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 25.dp)
            ) { calendar ->
                Toast.makeText(applicationContext,
                    sdf.format(calendar.timeInMillis),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}