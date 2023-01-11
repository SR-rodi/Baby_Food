package com.example.artyomkafood.feature_food.presentation.init

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.core.database.InitDataBase
import com.example.artyomkafood.core.database.entity.CategoryEntity
import com.example.artyomkafood.core.database.entity.ProductEntity
import com.example.artyomkafood.feature_food.domain.repository.CategoryRepository
import com.example.artyomkafood.feature_food.domain.repository.MealRepository
import com.example.artyomkafood.feature_food.domain.repository.ProductRepository
import com.example.artyomkafood.feature_food.domain.repository.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartViewModel(
    private val categoryRepository: CategoryRepository,
    private val foodRepository: ProductRepository,
    private val mealRepository: MealRepository,
    private val scheduleRepository: ScheduleRepository,
) : ViewModel() {

    private val _data = MutableStateFlow(false)
    val data = _data.asStateFlow()

    fun setInfoInDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            InitDataBase.categoryList.forEach { categoryName ->
                categoryRepository.addCategory(CategoryEntity(name = categoryName))
            }
            InitDataBase.productList.forEach { foodName ->
                foodRepository.addFood(ProductEntity(name = foodName))
            }
            InitDataBase.mergeList.forEach { merge ->
                foodRepository.insertMergeProductInCategory(merge)
            }
            InitDataBase.mealList.forEach { entity ->
                mealRepository.addMeal(entity)
            }
            scheduleRepository.addListSchedule(InitDataBase.scheduleList)
            InitDataBase.mergeListMeal().forEach { merge ->
                mealRepository.insertMergeMeal(merge)
            }
            _data.value = true
        }
    }

    fun startNewFragment() {
        _data.value = true
    }
}