package com.example.customcalendar.customCalendar.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.customcalendar.R
import com.example.customcalendar.customCalendar.data.Date
import java.util.*


abstract class CustomCalendarViewModel : ViewModel() {

    protected val calendar = Calendar.getInstance()

    protected val _listDate: MutableLiveData<List<Date>> =
        MutableLiveData(emptyList())
    val listDate: LiveData<List<Date>> = _listDate

    protected val _monthText: MutableLiveData<String> = MutableLiveData("")
    val monthText: LiveData<String> = _monthText

    protected val _isClickArrow: MutableLiveData<Boolean> = MutableLiveData(true)
    val isClickArrow: LiveData<Boolean> = _isClickArrow

    protected val mapMonth =
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


    protected fun getStartDayOfWeek(month: Int): Int {
        val newCalendar = Calendar.getInstance()
        newCalendar.set(Calendar.MONTH, month)
        newCalendar.set(Calendar.DAY_OF_MONTH, 1)
        var dayOfWeek = newCalendar.get(Calendar.DAY_OF_WEEK)
        dayOfWeek = if (dayOfWeek == 1) 7 else dayOfWeek - 1

        return dayOfWeek
    }

    protected fun getMaxDayOfMonth(month: Int): Int {

        val newCalendar = Calendar.getInstance()
        newCalendar.set(Calendar.MONTH, month)
        return newCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    protected fun setNextMonth() {
        if (calendar.get(Calendar.MONTH) != Calendar.DECEMBER)
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1)
        else {
            calendar.set(Calendar.MONTH, Calendar.JANUARY)
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1)
        }
    }

    protected fun setPastMonth() {
        if (calendar.get(Calendar.MONTH) != Calendar.JANUARY)
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1)
        else {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1)
            calendar.set(Calendar.MONTH, Calendar.DECEMBER)
        }
    }


    fun Int.toPastMonth() =
        if (this - 1 == 0) 11 else this - 1

    companion object {
        const val COLUMNS = 7
        const val IMAGE_ARROW_ID = R.drawable.ic_arrow
        const val ID_ARROW_NEXT = 1
        const val ID_ARROW_PAST = -1

    }
}