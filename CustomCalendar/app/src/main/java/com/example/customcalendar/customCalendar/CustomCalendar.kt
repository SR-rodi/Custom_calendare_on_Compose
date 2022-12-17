package com.example.customcalendar.customCalendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.customcalendar.customCalendar.CustomCalendarViewModel.Companion.IMAGE_ARROW_ID
import com.example.customcalendar.ui.theme.Green
import java.util.*

@ExperimentalFoundationApi
@Composable
fun CustomCalendar(
    viewModel: CalendarViewModel,
    modifier: Modifier = Modifier,
    titleFontFamily: FontFamily = FontFamily.Default,
    tittleFontWeight: FontWeight = FontWeight.Bold,
    rippleColor: Color = Color.Blue,
    weekFontFamily: FontFamily = FontFamily.Default,
    weekFontWeight: FontWeight = FontWeight.Medium,
    selectorColor: Color = Color.LightGray,
    dateFontFamily: FontFamily = FontFamily.Default,
    dateFontWeight: FontWeight = FontWeight.Light,
    inactiveDateColor: Color = Color.LightGray,
    activeDateColor: Color = Color.Black,
    weekendDateColor: Color = Green,
    onClickData: (calendar: Calendar) -> Unit,
) {

    val monthNameState = viewModel.monthText.observeAsState("")
    val listDateState = viewModel.listDate.observeAsState(emptyList())
    val isClickArrow = viewModel.isClickArrow.observeAsState(true)

    Column(modifier = modifier) {

        MonthPicker(
            monthName = monthNameState.value,
            arrowImageId = IMAGE_ARROW_ID,
            font = titleFontFamily,
            fontWeight = tittleFontWeight,
            rippleColor = rippleColor
        ) { arrowId -> viewModel.onClickArrow(arrowId) }

        DataPicker(
            dateList = listDateState.value,
            isClickArrow = isClickArrow.value,
            weekFontFamily = weekFontFamily,
            weekFontWeight = weekFontWeight,
            dateFontFamily = dateFontFamily,
            dateFontWeight = dateFontWeight,
            selectorColor = selectorColor,
            inactiveDateColor=inactiveDateColor,
            activeDateColor=activeDateColor,
            weekendDateColor=weekendDateColor,
        ) { date -> onClickData(viewModel.createCalendar(date)) }
    }
}