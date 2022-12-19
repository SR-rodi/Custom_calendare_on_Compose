package com.example.customcalendar.customCalendar.data

import com.example.customcalendar.customCalendar.presentation.ID_ARROW_NEXT
import com.example.customcalendar.customCalendar.presentation.ID_ARROW_PAST
import java.util.*

object CalendarBuilder {

    private val calendar = Calendar.getInstance()
    private var selectMonth = calendar.get(Calendar.MONTH)

    private val mapMonth =
        mapOf(Pair(0, "Январь"),
            Pair(1, "Февраль"),
            Pair(2, "Март"),
            Pair(3, "Апрель"),
            Pair(4, "Май"),
            Pair(5, "Июнь"),
            Pair(6, "Июль"),
            Pair(7, "Август"),
            Pair(8, "Сентябрь"),
            Pair(9, "Октябрь"),
            Pair(10, "Ноябрь"),
            Pair(11, "Декабрь"))

    fun getCountDay() = calendar.get(Calendar.DAY_OF_MONTH)

    fun getMonth() = calendar.get(Calendar.MONTH)

    fun getPastMonth() = calendar.get(Calendar.MONTH).toPastMonth()

    fun getStartDayOfWeek(): Int {
        val newCalendar = Calendar.getInstance()
        newCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH))
        newCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
        newCalendar.set(Calendar.DAY_OF_MONTH, 1)
        var dayOfWeek = newCalendar.get(Calendar.DAY_OF_WEEK)
        dayOfWeek = if (dayOfWeek == 1) 7 else dayOfWeek - 1

        return dayOfWeek
    }

    fun getMaxDayOfMonth(month: Int): Int {

        val newCalendar = Calendar.getInstance()
        newCalendar.set(Calendar.MONTH, month)
        return newCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    fun getMonthText() =
        "${mapMonth[calendar.get(Calendar.MONTH)]} ${calendar.get(Calendar.YEAR)}"

    fun setNextMonth() {
        if (calendar.get(Calendar.MONTH) != Calendar.DECEMBER)
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1)
        else {
            calendar.set(Calendar.MONTH, Calendar.JANUARY)
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1)
        }
    }

    fun setPastMonth() {
        if (calendar.get(Calendar.MONTH) != Calendar.JANUARY)
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1)
        else {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1)
            calendar.set(Calendar.MONTH, Calendar.DECEMBER)
        }
    }

    fun createCalendar (): List<Date> {
        val list = mutableListOf<Date>()
        val startDay = getStartDayOfWeek()
        val dayOfPastMonth = getMaxDayOfMonth(getPastMonth())
        val dayOfCounterMonth = getMaxDayOfMonth(getMonth())

        val startDayOfPastMonth = dayOfPastMonth - startDay + 1

        if (startDay != 1)
            for (i in 1 until startDay)
                list.add(Date(startDayOfPastMonth + i, isPastMonth = true))

        for (i in 1..dayOfCounterMonth) {
            if ((i + startDay) % 7 == 0 || (i + startDay - 1) % 7 == 0) {

                list.add(Date(i, isWeekend = true))
            } else list.add(Date(i))
        }
        return list
    }

    fun newCalendar(date: Date): Calendar {

        if (date.isPastMonth) {
            setPastMonth()
            getMonthText()
            createCalendar()
            selectMonth = calendar.get(Calendar.MONTH)
        }

        calendar.set(Calendar.DAY_OF_MONTH, date.day)

        return calendar
    }

    fun onClickArrow(arrowId: Int) {

        when (arrowId) {
            ID_ARROW_NEXT -> CalendarBuilder.setNextMonth()
            ID_ARROW_PAST -> CalendarBuilder.setPastMonth()
            else -> throw IndexOutOfBoundsException("invalid index")
        }
    }

    fun Int.toPastMonth() =
        if (this - 1 == 0) 11 else this - 1
}