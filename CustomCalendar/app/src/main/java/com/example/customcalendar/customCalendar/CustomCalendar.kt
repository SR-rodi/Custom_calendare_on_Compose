package com.example.customcalendar.customCalendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.customcalendar.customCalendar.CustomCalendarViewModel.Companion.IMAGE_ARROW_ID
import java.util.*

@ExperimentalFoundationApi
@Composable
fun CustomCalendar(
    viewModel: CalendarViewModel,
    arrowPadding: Dp = 10.dp,
    onClickData: (calendar: Calendar) -> Unit,
) {

    val monthNameState = viewModel.monthText.observeAsState("")
    val listDateState = viewModel.listDate.observeAsState(emptyList())
    val isClickArrow = viewModel.isClickArrow.observeAsState(true)
    
    Column(modifier = Modifier.fillMaxSize()) {

        MonthPicker(
            monthName = monthNameState.value,
            arrowImageId = IMAGE_ARROW_ID,
            arrowPadding = arrowPadding,
            ) {arrowId-> viewModel.onClickArrow(arrowId) }

        DataPicker(
            dateList = listDateState.value,
            isClickArrow = isClickArrow.value
        ) {date-> onClickData(viewModel.createCalendar(date)) }
    }

}