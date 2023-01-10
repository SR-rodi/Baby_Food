package com.example.artyomkafood.feature_food.presentation.day

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.core.basemodel.BaseViewModel
import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.feature_food.domain.repository.MealRepository
import com.example.artyomkafood.feature_food.domain.repository.ScheduleRepository
import kotlinx.coroutines.Dispatchers
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

    private val calendar = Calendar.getInstance()

    fun setExpanded(position: Int) {
        viewModelScope.launch {
            data.collect {
                it[position].expanded = !it[position].expanded
            }
        }
    }

    fun getDate(): String =
        simpleDateFormat.format(calendar.timeInMillis)

    fun onClickDateButton(isNext: Boolean) {
        if (isNext) calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
        else calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1)
        getSchedule()
    }

    fun getSchedule() =
        viewModelScope.launch(Dispatchers.IO) {
            val schedule = scheduleRepository.getSchedule(setDate())
            scheduleRepository.getMeal(setDate()).onEach { mealSchedule ->
                schedule.forEach {
                    if (mealSchedule[it.name] != null)
                        it.meal = mealSchedule[it.name]!!
                }
                _data.emit(schedule)
            }.launchIn(viewModelScope)
        }

    fun setDate() =
        Calendar.getInstance().apply {
            clear()
            set(Calendar.YEAR, calendar.get(Calendar.YEAR))
            set(Calendar.MONTH, calendar.get(Calendar.MONTH))
            set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))
        }.timeInMillis

    fun onSwipeEvent(meal: ScheduleMeal?) =
        viewModelScope.launch(Dispatchers.IO) {
            if (meal != null)
                mealRepository.delete(MealEntity(meal.meal_volume, setDate(), meal.meal_id))
        }

    fun updateMeal(meal: ScheduleMeal?, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (meal != null)
                mealRepository.update(MealEntity(text.toInt(), meal.meal_data, meal.meal_id))
        }
    }


}