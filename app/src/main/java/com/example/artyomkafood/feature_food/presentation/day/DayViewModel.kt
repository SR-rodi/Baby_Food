package com.example.artyomkafood.feature_food.presentation.day

import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.feature_food.domain.repository.MealRepository
import com.example.artyomkafood.feature_food.domain.repository.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DayViewModel(
    private val scheduleRepository: ScheduleRepository,
    private val mealRepository: MealRepository,
) : CalendarViewModel<Schedule>() {

    @ExperimentalCoroutinesApi
    val schedules = query.asStateFlow().flatMapLatest { scheduleRepository.getSchedule(it) }

    fun onSwipeEvent(meal: ScheduleMeal?) = viewModelScope.launch(Dispatchers.IO) {
        if (meal != null) mealRepository.delete(
            MealEntity(
                meal.meal_volume, getDateLong(), meal.meal_id
            )
        )
    }

    fun updateMeal(meal: ScheduleMeal?, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (meal != null) mealRepository.update(
                MealEntity(text.toInt(), meal.meal_data, meal.meal_id)
            )
        }
    }

}