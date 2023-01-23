package com.example.artyomkafood.feature_food.domain.repository

import java.text.SimpleDateFormat

interface CalendarRepository {

    val simpleDateFormat: SimpleDateFormat

    fun createData()
    fun getMonth(): Int
    fun getDay(): Int
    fun getDayOfMonth(): Int
    fun getDateLong(): Long
    fun getDateString(): String

    fun setDay(position: Int)
    fun setDate(day: Int, month: Int, year: Int)

    fun createDayList(dayOfMonth:Int): MutableList<Int> {
        val newDateList = mutableListOf<Int>()
        for (i in 1..dayOfMonth) newDateList.add(i)
        return newDateList
    }
}