package com.example.artyomkafood.feature_food.presentation.day

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.core.basemodel.BaseViewModel
import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.feature_food.domain.repository.MealRepository
import com.example.artyomkafood.feature_food.domain.repository.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DayViewModel(
    private val scheduleRepository: ScheduleRepository,
    private val mealRepository: MealRepository,
) : BaseViewModel<Schedule>() {

    @SuppressLint("SimpleDateFormat")
    private val simpleDateFormat = SimpleDateFormat("dd.MM.yy")

    private var calendar = Calendar.getInstance()

    init {
        calendar = createDate()
    }

    private val query = MutableStateFlow(calendar.timeInMillis)

    @OptIn(ExperimentalCoroutinesApi::class)
    val schedules = query.asStateFlow()
        .flatMapLatest { scheduleRepository.getSchedule(it) }

    fun getMonth() = calendar.get(Calendar.MONTH)
    fun getDay() = calendar.get(Calendar.DAY_OF_MONTH)
    fun getDayOfMonth() = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    fun getDateLong() = calendar.timeInMillis

    fun getDateString(): String =
        simpleDateFormat.format(calendar.timeInMillis)

    fun createDayList(): MutableList<Int> {
        val dateList = mutableListOf<Int>()
        for (i in 1..getDayOfMonth())
            dateList.add(i)
        return dateList
    }

    private fun createDate(): Calendar =
        Calendar.getInstance().apply {
            clear()
            set(Calendar.YEAR, calendar.get(Calendar.YEAR))
            set(Calendar.MONTH, calendar.get(Calendar.MONTH))
            set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))
        }

    fun onSwipeEvent(meal: ScheduleMeal?) =
        viewModelScope.launch(Dispatchers.IO) {
            if (meal != null)
                mealRepository.delete(MealEntity(meal.meal_volume,
                    calendar.timeInMillis,
                    meal.meal_id))
        }

    fun updateMeal(meal: ScheduleMeal?, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (meal != null)
                mealRepository.update(MealEntity(text.toInt(), meal.meal_data, meal.meal_id))
        }
    }

    fun setDay(position: Int) {
        calendar.set(Calendar.DAY_OF_MONTH, position + 1)
        query.value = calendar.timeInMillis
    }

}