package com.example.customcalendar.customCalendar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.customcalendar.DayOfWeek
import com.example.customcalendar.customCalendar.CustomCalendarViewModel.Companion.COLUMNS
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun DataPicker(
    dateList: List<Date>,
    isClickArrow: Boolean,
    onClick: (date: Date) -> Unit,
) {

    val scaleEffect = remember {
        Animatable(initialValue = 0.3f)
    }
    LazyVerticalGrid(
        cells = GridCells.Fixed(COLUMNS),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.selectableGroup()
    ) {
        // рисуем недели
        items(DayOfWeek.values()) { dayOfWeek ->
            Text(text = dayOfWeek.text,
                textAlign = TextAlign.Center)
        }

        items(dateList) { date ->
            LaunchedEffect(key1 = date.isSelected) {
                launch {
                    scaleEffect.animateTo(
                        targetValue = 0.3f,
                        animationSpec = tween(
                            durationMillis = 350
                        )
                    )
                    scaleEffect.animateTo(
                        targetValue = 1f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                }
            }

            Box(Modifier
                .fillMaxSize()
                .scale(scale = if (isClickArrow) scaleEffect.value else 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    color = if (date.isPastMonth) Color.Gray else if (date.isWeekend) Color.Green else Color.Black,
                    textAlign = TextAlign.Center,
                    text = date.day.toString(),
                    modifier = Modifier
                        .fillMaxSize()
                        .scale(if (date.isSelected) scaleEffect.value else 1f)
                        .selectable(
                            selected = date.isSelected,
                            role = Role.RadioButton,
                            onClick = { onClick(date) }
                        )
                        .background(
                            color = if (date.isSelected) Color.Blue else Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                )
            }

        }
    }

}