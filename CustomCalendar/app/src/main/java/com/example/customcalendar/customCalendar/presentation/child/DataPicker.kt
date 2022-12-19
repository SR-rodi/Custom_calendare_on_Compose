package com.example.customcalendar.customCalendar.presentation.child

import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customcalendar.customCalendar.data.Date
import com.example.customcalendar.customCalendar.data.DayOfWeek
import com.example.customcalendar.customCalendar.presentation.COLUMNS
import com.example.customcalendar.customCalendar.data.CalendarBuilder
@ExperimentalFoundationApi
@Composable
fun DataPicker(
    dateListState: State<List<Date>>,
    scaleAnimation: Float = 1f,
    isClickArrow: Boolean = false,
    weekFontFamily: FontFamily = FontFamily.Default,
    weekFontWeight: FontWeight = FontWeight.Medium,
    selectorColor: Color = Color.LightGray,
    dateFontFamily: FontFamily = FontFamily.Default,
    dateFontWeight: FontWeight = FontWeight.Light,
    textSizeDate: TextUnit = 16.sp,
    textSizeWeek: TextUnit = 17.sp,
    inactiveDateColor: Color = Color.LightGray,
    activeDateColor: Color = Color.Black,
    weekendDateColor: Color = Color.Cyan,
    onClick: (date: Date) -> Unit,
) {

    val position = remember { mutableStateOf(CalendarBuilder.getCountDay()) }
    val transition = updateTransition(targetState = position, label = "")

    LazyVerticalGrid(
        cells = GridCells.Fixed(COLUMNS),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.selectableGroup()
    ) {

        items(DayOfWeek.values()) { dayOfWeek ->
            Text(text = dayOfWeek.text,
                textAlign = TextAlign.Center,
                fontFamily = weekFontFamily,
                fontWeight = weekFontWeight,
                fontSize = textSizeWeek,
                modifier = Modifier.padding(bottom = 10.dp))
        }


        itemsIndexed(dateListState.value) { index, date ->

            val durationAnimation = 600

            val scale = transition.animateFloat(
                transitionSpec = {
                    keyframes {
                        durationMillis = durationAnimation
                        0.7f at 0 with FastOutLinearInEasing // for 0-15 ms
                        0.3f at (durationAnimation * 0.3).toInt()
                        0.6f at (durationAnimation * 0.4).toInt() with LinearOutSlowInEasing
                        1.1f at (durationAnimation * 0.8).toInt() with LinearOutSlowInEasing
                        1f at durationAnimation with LinearOutSlowInEasing
                    }
                },
                label = "",
                targetValueByState = { position -> if (position.value == index) 1f else 0.9f }
            )

            if (index == position.value) date.isSelected = true
            Box(Modifier
                .padding(top = 5.dp)
                .width(45.dp)
                .height(36.dp)
                .clip(shape = RoundedCornerShape(360.dp))
                .scale(if (date.isSelected) scale.value else 1f)
                .selectable(
                    selected = date.isSelected,
                    role = Role.RadioButton,
                    onClick = {
                        dateListState.value[position.value].isSelected = false
                        position.value = index
                        dateListState.value[position.value].isSelected = true
                        onClick(date)
                    }
                )
                .background(
                    color = if (date.isSelected) selectorColor else Color.Transparent,
                    shape = RoundedCornerShape(360.dp)
                ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    color = if (date.isPastMonth) inactiveDateColor else if (date.isWeekend) weekendDateColor else activeDateColor,
                    text = date.day.toString(),
                    fontSize = textSizeDate,
                    textAlign = TextAlign.Center,
                    fontFamily = dateFontFamily,
                    fontWeight = dateFontWeight,
                    modifier = Modifier
                         .scale(scale = scaleAnimation)
                        .clip(shape = RoundedCornerShape(360.dp))
                )
            }

        }
    }
}