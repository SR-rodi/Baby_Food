package com.example.artyomkafood.feature_food.presentation.day.viewmodel

import com.example.artyomkafood.core.basemodel.BaseViewModel
import com.example.artyomkafood.feature_food.domain.repository.CalendarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class CalendarViewModel<T : Any>(
    private val calendarRepository: CalendarRepository,
) : BaseViewModel<T>() {

    fun getMonth() = calendarRepository.getMonth()
    fun getDay() = calendarRepository.getDay()
    fun getDayOfMonth() = calendarRepository.getDayOfMonth()
    fun getDateLong() = calendarRepository.getDateLong()
    fun getDateString(): String = calendarRepository.getDateString()

    protected val query = MutableStateFlow(getDateLong())

    private val _dateList = MutableStateFlow<MutableList<Int>>(mutableListOf())
    val dateList = _dateList.asStateFlow()

    init {
        calendarRepository.createData()
        createDayList()
    }

    fun setDay(position: Int) {
        calendarRepository.setDay(position)
        query.value = getDateLong()
    }

    fun setDate(day: Int, month: Int, year: Int) {
        calendarRepository.setDate(day, month, year)
        if (_dateList.value.size != getDayOfMonth()) createDayList()
        query.value = getDateLong()
    }

    private fun createDayList() {
        _dateList.value = calendarRepository.createDayList(getDayOfMonth())
    }
}