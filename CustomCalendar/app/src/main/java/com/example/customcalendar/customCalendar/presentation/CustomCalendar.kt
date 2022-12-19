@file:Suppress("NAME_SHADOWING")

package com.example.customcalendar.customCalendar.presentation

import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.customcalendar.R
import com.example.customcalendar.customCalendar.data.CalendarBuilder
import com.example.customcalendar.customCalendar.data.Date
import com.example.customcalendar.customCalendar.presentation.child.DataPicker
import com.example.customcalendar.customCalendar.presentation.child.MonthPicker
import java.util.*


const val COLUMNS = 7
const val IMAGE_ARROW_ID = R.drawable.ic_arrow
const val ID_ARROW_NEXT = 1
const val ID_ARROW_PAST = -1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomCalendar(
    calendarState: State<List<Date>>,
    modifier: Modifier = Modifier,
    textSizeMonth: TextUnit = 18.sp,
    textSizeWeek: TextUnit = 16.sp,
    textSizeDate: TextUnit = 15.sp,
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
    weekendDateColor: Color = Color(0xFF589A6E),
    onClickMonth: () -> Unit,
    onClickDate: (calendar: Calendar) -> Unit,
) {

    var nameCalendar by remember { mutableStateOf(CalendarBuilder.getMonthText()) }

    val transition = updateTransition(targetState = nameCalendar, label = "")

    val durationAnimation = 1000

    val scale = transition.animateFloat(
        transitionSpec = {
            keyframes {
                durationMillis = durationAnimation
                0.7f at 0 with FastOutLinearInEasing
                0.3f at (durationAnimation * 0.3).toInt()
                0.6f at (durationAnimation * 0.4).toInt() with LinearOutSlowInEasing
                1.1f at (durationAnimation * 0.8).toInt() with LinearOutSlowInEasing
                1f at durationAnimation with LinearOutSlowInEasing
            }
        },
        label = "",
        targetValueByState = { nameCalendar -> if (nameCalendar != "") 1f else 0.9f }
    )

    Column(modifier = modifier) {
        MonthPicker(
            monthName = nameCalendar,
            arrowImageId = IMAGE_ARROW_ID,
            font = titleFontFamily,
            fontWeight = tittleFontWeight,
            textSizeMonth = textSizeMonth,
            rippleColor = rippleColor
        ) {
            CalendarBuilder.onClickArrow(it)
            onClickMonth()
            nameCalendar = CalendarBuilder.getMonthText()
        }

        DataPicker(
            dateListState = calendarState,
            scaleAnimation = scale.value,
            weekFontFamily = weekFontFamily,
            weekFontWeight = weekFontWeight,
            textSizeDate= textSizeDate,
            textSizeWeek= textSizeWeek,
            dateFontFamily = dateFontFamily,
            dateFontWeight = dateFontWeight,
            selectorColor = selectorColor,
            inactiveDateColor=inactiveDateColor,
            activeDateColor=activeDateColor,
            weekendDateColor=weekendDateColor,
        )
        { date ->
            onClickDate(CalendarBuilder.newCalendar(date))
        }
    }
}

@Composable
fun calendarState(): MutableState<List<Date>> {
    val list = CalendarBuilder.createCalendar()
    return produceState(initialValue = list, producer = {
        value = list
    }) as MutableState<List<Date>>
}
