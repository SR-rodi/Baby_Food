package com.example.artyomkafood.feature_init.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.core.InitDataBase
import com.example.artyomkafood.core.database.entity.CategoryEntity
import com.example.artyomkafood.core.database.entity.ProductEntity
import com.example.artyomkafood.core.database.entity.merge.ProductAndMealAndScheduleEntity
import com.example.artyomkafood.feature_food.domain.model.FoodCategory
import com.example.artyomkafood.feature_food.domain.repository.CategoryRepository
import com.example.artyomkafood.feature_food.domain.repository.MealRepository
import com.example.artyomkafood.feature_food.domain.repository.ProductRepository
import com.example.artyomkafood.feature_food.domain.repository.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class StartViewModel(
    private val categoryRepository: CategoryRepository,
    private val foodRepository: ProductRepository,
    private val mealRepository: MealRepository,
    private val scheduleRepository: ScheduleRepository,
) : ViewModel() {

    private val _data = MutableSharedFlow<List<FoodCategory>>()
    val data = _data.asSharedFlow()

    fun setInfoInDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            var category = categoryRepository.getAllCategory()
            if (category.isEmpty()) {
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

            }
            _data.emit(category)

        }
    }
}