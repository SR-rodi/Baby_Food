package com.example.artyomkafood.feature_food.presentation.add.chldren

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.merge.ProductAndMealAndScheduleEntity
import com.example.artyomkafood.feature_food.domain.model.FoodMeal
import com.example.artyomkafood.feature_food.domain.model.FoodProduct
import com.example.artyomkafood.feature_food.domain.repository.MealRepository
import com.example.artyomkafood.feature_food.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProductTabViewModel(
    private val foodRepository: ProductRepository,
    private val mealRepository: MealRepository,
) : ViewModel() {

    private val _data = MutableSharedFlow<List<FoodProduct>>()
    val data = _data.asSharedFlow()

    private val checkList = mutableMapOf<Int, FoodProduct>()

    fun startFragment(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _data.emit(foodRepository.getFoodByCategoryId(id))
        }
    }

    fun addCheckList(item: FoodProduct) {
        if (item.id != null)
            if (checkList[item.id] == null) checkList[item.id] = item
            else checkList.remove(item.id)
    }

    fun workDatabase(date: Long,scheduleId:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            checkList.values.forEach { food ->
                mealRepository.addMeal(MealEntity(food.volume, date))
                val lastIndex = mealRepository.getLastIndex()
                mealRepository.insertMergeMeal(ProductAndMealAndScheduleEntity(food.id!!,lastIndex,scheduleId))
            }
        }
    }
}