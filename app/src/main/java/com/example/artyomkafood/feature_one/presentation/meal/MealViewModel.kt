package com.example.artyomkafood.feature_one.presentation.meal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.feature_one.domain.model.FoodMeal
import com.example.artyomkafood.feature_one.domain.repository.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MealViewModel(
    private val mealRepository: MealRepository,
) : ViewModel() {

    private val _data = MutableSharedFlow<List<FoodMeal>>()
    val data = _data.asSharedFlow()

    fun getData(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.emit(mealRepository.getMealByProductId(id))
        }
    }
}