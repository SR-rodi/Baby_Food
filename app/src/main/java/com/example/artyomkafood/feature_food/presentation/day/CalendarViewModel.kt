package com.example.artyomkafood.feature_food.presentation.day

import android.annotation.SuppressLint
import com.example.artyomkafood.core.basemodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.*

abstract class CalendarViewModel<T : Any> : BaseViewModel<T>() {

    @SuppressLint("SimpleDateFormat")
    val simpleDateFormat = SimpleDateFormat("dd.MM.yy")

    private var calendar = Calendar.getInstance()

    fun getMonth() = calendar.get(Calendar.MONTH)
    fun getDay() = calendar.get(Calendar.DAY_OF_MONTH)
    fun getDayOfMonth() = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    fun getDateLong() = calendar.timeInMillis
    fun getDateString(): String = simpleDateFormat.format(calendar.timeInMillis)

    protected val query = MutableStateFlow(calendar.timeInMillis)

    private val _dateList = MutableStateFlow<MutableList<Int>>(mutableListOf())
    val dateList = _dateList.asStateFlow()

    init {
        calendar = createDate()
        createDayList()
    }


    fun setDay(position: Int) {
        calendar.set(Calendar.DAY_OF_MONTH, position + 1)
        query.value = calendar.timeInMillis
    }

    fun setDate(day: Int, month: Int, year: Int) {
        calendar.apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
        }
        if (_dateList.value.size != getDayOfMonth()) createDayList()
        query.value = calendar.timeInMillis
    }

    private fun createDate(): Calendar = Calendar.getInstance().apply {
        clear()
        set(Calendar.YEAR, calendar.get(Calendar.YEAR))
        set(Calendar.MONTH, calendar.get(Calendar.MONTH))
        set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))
    }

    private fun createDayList() {
        val newDateList = mutableListOf<Int>()
        for (i in 1..getDayOfMonth()) newDateList.add(i)
        _dateList.value = newDateList
    }
}