package com.example.artyomkafood.feature_food.presentation.add.chldren

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.core.basemodel.BaseViewModel
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.merge.ProductAndMealAndScheduleEntity
import com.example.artyomkafood.feature_food.data.SettingsCategoryAdapter
import com.example.artyomkafood.feature_food.domain.model.FoodProduct
import com.example.artyomkafood.feature_food.domain.repository.MealRepository
import com.example.artyomkafood.feature_food.domain.repository.ProductRepository
import com.example.artyomkafood.feature_food.presentation.add.AddViewModel.Companion.SETTING_CHILDREN_FRAGMENT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductTabViewModel(
    private val foodRepository: ProductRepository,
    private val mealRepository: MealRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<FoodProduct>() {

    private val checkList = mutableMapOf<Int, FoodProduct>()

    private val setting: SettingsCategoryAdapter?=savedStateHandle[SETTING_CHILDREN_FRAGMENT]

    fun startFragment() =
        viewModelScope.launch(Dispatchers.IO) {
            if (setting!=null)
                _data.emit(foodRepository.getFoodByCategoryId(setting.position))
        }

    fun addCheckList(item: FoodProduct) {
        if (item.id != null)
            if (checkList[item.id] == null) checkList[item.id] = item
            else checkList.remove(item.id)
    }

    fun workDatabase() =
        viewModelScope.launch(Dispatchers.IO) {
            if (setting!=null)
                checkList.values.forEach { food ->
                mealRepository.addMeal(MealEntity(food.volume, setting.date))
                val lastIndex = mealRepository.getLastIndex()
                mealRepository.insertMergeMeal(
                    ProductAndMealAndScheduleEntity(food.id!!, lastIndex, setting.scheduleId))
            }

        }
}