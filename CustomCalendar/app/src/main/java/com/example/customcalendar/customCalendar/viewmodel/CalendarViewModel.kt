package com.example.customcalendar.customCalendar.viewmodel

import android.util.Log
import com.example.customcalendar.customCalendar.data.Date
import java.util.*

class CalendarViewModel : CustomCalendarViewModel() {
    private var selectMonth = calendar.get(Calendar.MONTH)

    init {
        createDateList()
        getMonthText()
    }

    fun onClickArrow(id: Int) {
        when (id) {
            ID_ARROW_NEXT -> setNextMonth()
            ID_ARROW_PAST -> setPastMonth()
            else -> throw IndexOutOfBoundsException("invalid index")
        }
        _isClickArrow.postValue(true)
        createDateList()
        getMonthText()
    }

    private fun createDateList() {

        val startDay = getStartDayOfWeek(calendar.get(Calendar.MONTH))
        val dayOfPastMonth = getMaxDayOfMonth(calendar.get(Calendar.MONTH).toPastMonth())
        val dayOfCounterMonth = getMaxDayOfMonth(calendar.get(Calendar.MONTH))

        val list = mutableListOf<Date>()
        val startDayOfPastMonth = dayOfPastMonth - startDay + 1

        if (startDay != 1)
            for (i in 1 until startDay)
                list.add(Date(startDayOfPastMonth + i, isPastMonth = true))

        for (i in 1..dayOfCounterMonth) {
            Log.e("Kart", ((i + startDay) % 7).toString())
            if ((i + startDay) % 7 == 0 || (i + startDay - 1) % 7 == 0) {

                list.add(Date(i, isWeekend = true))
            } else list.add(Date(i))
        }
        if (selectMonth == calendar.get(Calendar.MONTH))
            list[calendar.get(Calendar.DAY_OF_MONTH) + startDay - 2].isSelected = true
        _listDate.postValue(list)
    }

    private fun getMonthText() {
        _monthText.value =
            "${mapMonth[calendar.get(Calendar.MONTH)]} ${calendar.get(Calendar.YEAR)}"
    }

    fun createCalendar(date: Date): Calendar {

        if (date.isPastMonth) {
            _isClickArrow.postValue(true)
            setPastMonth()
            getMonthText()
        } else _isClickArrow.postValue(false)

        selectMonth = calendar.get(Calendar.MONTH)
        calendar.set(Calendar.DAY_OF_MONTH, date.day)
        createDateList()

        return calendar
    }
}