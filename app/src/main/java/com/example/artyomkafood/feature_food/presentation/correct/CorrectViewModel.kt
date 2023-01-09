package com.example.artyomkafood.feature_food.presentation.correct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.feature_food.domain.repository.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CorrectViewModel(
    private val mealRepository: MealRepository
) : ViewModel() {

    fun updateMeal(meal: ScheduleMeal, text: String){
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.update(MealEntity( text.toInt(),meal.meal_data,meal.meal_id))
        }
    }
}