package com.example.artyomkafood.feature_food.presentation.add.chldren

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.core.basemodel.BaseViewModel
import com.example.artyomkafood.feature_food.data.SettingsCategoryAdapter
import com.example.artyomkafood.feature_food.domain.model.FoodProduct
import com.example.artyomkafood.feature_food.domain.repository.MealRepository
import com.example.artyomkafood.feature_food.domain.repository.ProductRepository
import com.example.artyomkafood.feature_food.presentation.add.AddViewModel.Companion.SETTING_CHILDREN_FRAGMENT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ProductTabViewModel(
    private val foodRepository: ProductRepository,
    private val mealRepository: MealRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<FoodProduct>() {

    private val checkList = mutableMapOf<Int, FoodProduct>()

    private val setting: SettingsCategoryAdapter? = savedStateHandle[SETTING_CHILDREN_FRAGMENT]

    fun startFragment() {
        if (setting != null)
            foodRepository.getFoodByCategoryId(setting.position).onEach {
                _data.emit(it)
            }.launchIn(viewModelScope)
    }


    fun addCheckList(item: FoodProduct) {
        if (item.id != null)
            if (checkList[item.id] == null) checkList[item.id] = item
            else checkList.remove(item.id)
    }

    fun updateProduct(product: FoodProduct, newValue: String) {
        viewModelScope.launch(Dispatchers.IO) {
            product.volume = newValue.toInt()
            foodRepository.updateProduct(product)
        }
    }

    fun addMeal() =
        viewModelScope.launch(Dispatchers.IO) {
            if (setting != null)
                mealRepository.addMealAndMerge(checkList, setting.scheduleId, setting.date)
        }
}