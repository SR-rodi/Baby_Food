package com.example.artyomkafood.feature_food.data.calendar

import android.annotation.SuppressLint
import com.example.artyomkafood.feature_food.domain.repository.CalendarRepository
import java.text.SimpleDateFormat
import java.util.Calendar

class CalendarRepositoryImpl(private val calendar: Calendar) : CalendarRepository {

    override fun createData() {
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        calendar.apply {
            clear()
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
        }
    }

    @SuppressLint("SimpleDateFormat")
    override val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd.MM.yy")

    override fun getMonth() = calendar.get(Calendar.MONTH)

    override fun getDay() = calendar.get(Calendar.DAY_OF_MONTH)

    override fun getDayOfMonth() = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    override fun getDateLong() = calendar.timeInMillis

    override fun getDateString(): String = simpleDateFormat.format(calendar.timeInMillis)

    override fun setDay(position: Int) = calendar.set(Calendar.DAY_OF_MONTH, position + 1)

    override fun setDate(day: Int, month: Int, year: Int) {
        calendar.apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
        }
    }

}