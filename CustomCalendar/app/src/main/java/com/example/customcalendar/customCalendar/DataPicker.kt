package com.example.customcalendar.customCalendar

import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import com.example.customcalendar.customCalendar.CustomCalendarViewModel.Companion.COLUMNS
import com.example.customcalendar.customCalendar.data.Date
import com.example.customcalendar.customCalendar.data.DayOfWeek

@ExperimentalFoundationApi
@Composable
fun DataPicker(
    dateList: List<Date>,
    isClickArrow: Boolean,
    weekFontFamily: FontFamily,
    weekFontWeight: FontWeight,
    selectorColor:Color,
    dateFontFamily: FontFamily,
    dateFontWeight: FontWeight,
    inactiveDateColor: Color,
    activeDateColor: Color,
    weekendDateColor: Color,
    onClick: (date: Date) -> Unit,
) {

    val scaleEffect: Animatable<Float, AnimationVector1D> = remember { Animatable(initialValue = 0.3f) }

    LazyVerticalGrid(
        cells = GridCells.Fixed(COLUMNS),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.selectableGroup()
    ) {
        // рисуем недели
        items(DayOfWeek.values()) { dayOfWeek ->
            Text(text = dayOfWeek.text,
                textAlign = TextAlign.Center,
                fontFamily = weekFontFamily,
                fontWeight = weekFontWeight,
                modifier = Modifier.padding(bottom = 10.dp))
        }

        items(dateList) { date ->
            ScaleAnimation(date.isSelected,scaleEffect)

            Box(Modifier
                .padding(top = 5.dp)
                .width(45.dp)
                .height(36.dp)
                .clip(shape = RoundedCornerShape(360.dp))
                .scale(if (date.isSelected) scaleEffect.value else 1f)
                .selectable(
                    selected = date.isSelected,
                    role = Role.RadioButton,
                    onClick = { onClick(date) }
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
                    textAlign = TextAlign.Center,
                    fontFamily = dateFontFamily,
                    fontWeight = dateFontWeight,
                    modifier = Modifier
                        .scale(scale = if (isClickArrow) scaleEffect.value else 1f)
                        .clip(shape = RoundedCornerShape(360.dp))
                )
            }

        }
    }
}