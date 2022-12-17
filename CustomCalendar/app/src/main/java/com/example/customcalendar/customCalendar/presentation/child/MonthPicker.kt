package com.example.customcalendar.customCalendar.presentation.child

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.customcalendar.customCalendar.viewmodel.CustomCalendarViewModel.Companion.ID_ARROW_NEXT
import com.example.customcalendar.customCalendar.viewmodel.CustomCalendarViewModel.Companion.ID_ARROW_PAST

@Composable
fun MonthPicker(
    monthName: String,
    arrowImageId: Int,
    font:FontFamily,
    fontWeight: FontWeight,
    rippleColor: Color,
    onClick: (idArrow: Int) -> Unit,
){

    val rippleNext = rememberRipple(color = rippleColor )
    val ripplePast = rememberRipple(color = rippleColor )
    val sourceNext = remember{MutableInteractionSource()}
    val sourcePast = remember{MutableInteractionSource()}

    Row(modifier = Modifier.padding(bottom = 28.dp),
    verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(360.dp))
                .clickable(
                    interactionSource = sourcePast,
                    indication = ripplePast,
                ) { onClick(ID_ARROW_PAST) },
            painter = painterResource(id = arrowImageId),
            contentDescription = "")
        Text(
            modifier = Modifier.weight(1f),
            text = monthName,
            textAlign = TextAlign.Center,
            fontWeight = fontWeight,
            fontFamily = font
        )
        Image(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(360.dp))
                .rotate(180f)
                .clickable(
                    interactionSource = sourceNext,
                    indication = rippleNext,
                ) { onClick(ID_ARROW_NEXT) },
            painter = painterResource(id = arrowImageId),
            contentDescription = "")
    }
}