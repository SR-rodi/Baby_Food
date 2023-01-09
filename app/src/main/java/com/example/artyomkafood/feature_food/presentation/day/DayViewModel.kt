package com.example.artyomkafood.feature_food.presentation.day

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.core.basemodel.BaseViewModel
import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.di.viewModelModel
import com.example.artyomkafood.feature_food.domain.repository.MealRepository
import com.example.artyomkafood.feature_food.domain.repository.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

class DayViewModel(
    private val scheduleRepository: ScheduleRepository,
    private val mealRepository: MealRepository,
) : BaseViewModel<Schedule>() {

    @SuppressLint("SimpleDateFormat")
    private val simpleDateFormat = SimpleDateFormat("dd.MM.yy")

    private val calendar = Calendar.getInstance()

    fun getDate(): String =
        simpleDateFormat.format(calendar.timeInMillis)

    fun onClickDateButton(isNext: Boolean) {
        if (isNext) calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
        else calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1)
        getSchedule()
    }

    fun getSchedule()=
        viewModelScope.launch(Dispatchers.IO) {
            val schedule = scheduleRepository.getSchedule()
            val meal = scheduleRepository.getMeal(setDate())

            meal.forEach { scheduleMeal ->
                schedule[scheduleMeal.schedule_id_merge - 1].meal.add(scheduleMeal)
            }
            _data.emit(schedule)
        }

    fun setDate() =
        Calendar.getInstance().apply {
            clear()
            set(Calendar.YEAR, calendar.get(Calendar.YEAR))
            set(Calendar.MONTH, calendar.get(Calendar.MONTH))
            set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))
        }.timeInMillis

    fun onSwipeEvent(meal: ScheduleMeal) =
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.delete(MealEntity(meal.meal_volume, setDate(), meal.meal_id))
        }
}