package com.example.customcalendar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.customcalendar.customCalendar.CalendarViewModel
import com.example.customcalendar.customCalendar.CustomCalendar
import com.example.customcalendar.ui.theme.CustomCalendarTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
  private val viewModel = CalendarViewModel()
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomCalendar(viewModel = viewModel){ calendar->
                Toast.makeText(applicationContext, "${calendar.get(Calendar.DAY_OF_MONTH)}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}