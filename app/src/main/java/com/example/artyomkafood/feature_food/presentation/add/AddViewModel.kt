package com.example.artyomkafood.feature_food.presentation.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.core.basemodel.BaseViewModel
import com.example.artyomkafood.feature_food.data.SettingsCategoryAdapter
import com.example.artyomkafood.feature_food.domain.model.FoodCategory
import com.example.artyomkafood.feature_food.domain.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(
    private val categoryRepository: CategoryRepository,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<FoodCategory>() {

    fun getCategory() =
        viewModelScope.launch(Dispatchers.IO) {
            _data.emit(categoryRepository.getAllCategory())
        }

    fun settingChildren(position: Int, date: Long, scheduleId: Int) {
        savedStateHandle[SETTING_CHILDREN_FRAGMENT] =
            SettingsCategoryAdapter(date, scheduleId, position)
    }

    companion object {
        const val SETTING_CHILDREN_FRAGMENT = "settingChildren"
    }
}