package com.example.artyomkafood.feature_food.presentation.allmeal

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.core.basemodel.BaseViewModel
import com.example.artyomkafood.core.database.entity.MealAndProduct
import com.example.artyomkafood.feature_food.domain.repository.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchMealViewModel(
    private val mealRepository: MealRepository,
) : BaseViewModel<MealAndProduct>() {

    init {
        getMeal()
    }

    private val query = MutableStateFlow("")

    private fun getMeal() =
        viewModelScope.launch(Dispatchers.IO) {
            val mealList = mealRepository.getAllMeal()

            query.collect { newQuery ->
                val newList = mealList.filter { meal ->
                    meal.productName.take(newQuery.length) == newQuery
                }
                _data.emit(newList)
            }
        }

    fun setQuery(newQuery: String) {
        query.value = newQuery
    }

}