package com.example.artyomkafood.feature_init.presentation.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.feature_food.domain.model.FoodProduct
import com.example.artyomkafood.feature_food.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FoodViewModel(
    private val foodRepository: ProductRepository,
) : ViewModel() {

    private val _data = MutableSharedFlow<List<FoodProduct>>()
    val data = _data.asSharedFlow()

    fun getFood(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
           _data.emit(foodRepository.getFoodByCategoryId(id))
        }
    }

}