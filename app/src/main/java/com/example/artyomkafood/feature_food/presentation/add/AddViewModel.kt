package com.example.artyomkafood.feature_food.presentation.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.feature_food.domain.model.FoodCategory
import com.example.artyomkafood.feature_food.domain.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddViewModel(
    private val categoryRepository: CategoryRepository,
) : ViewModel() {

    private val _data = MutableSharedFlow<List<FoodCategory>>()
    val data = _data.asSharedFlow()

    fun getCategory() {
        viewModelScope.launch(Dispatchers.IO) {
           val a= categoryRepository.getAllCategory()
            _data.emit(a)

        }
    }


}