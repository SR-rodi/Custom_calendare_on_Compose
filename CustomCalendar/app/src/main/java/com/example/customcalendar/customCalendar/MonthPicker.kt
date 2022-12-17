package com.example.customcalendar.customCalendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.customcalendar.customCalendar.CustomCalendarViewModel.Companion.ID_ARROW_NEXT
import com.example.customcalendar.customCalendar.CustomCalendarViewModel.Companion.ID_ARROW_PAST

@Composable
fun MonthPicker(
    monthName: String,
    arrowImageId: Int,
    arrowPadding: Dp,
    onClick: (idArrow: Int) -> Unit,
){

    Row() {
        Image(
            modifier = Modifier
                .padding(start = arrowPadding)
                .clickable { onClick(ID_ARROW_PAST) },
            painter = painterResource(id = arrowImageId),
            contentDescription = "")
        Text(modifier = Modifier.weight(1f),
            text = monthName,
            textAlign = TextAlign.Center
        )
        Image(
            modifier = Modifier
                .padding(end = arrowPadding)
                .rotate(180f)
                .clickable { onClick(ID_ARROW_NEXT) },
            painter = painterResource(id = arrowImageId),
            contentDescription = "")
    }
}