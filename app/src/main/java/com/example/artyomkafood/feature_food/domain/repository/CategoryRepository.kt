package com.example.artyomkafood.feature_food.domain.repository

import com.example.artyomkafood.core.database.entity.CategoryEntity
import com.example.artyomkafood.feature_food.domain.model.FoodCategory

interface CategoryRepository {

    suspend fun addCategory(category: CategoryEntity)

    suspend fun deleteCategory(category: CategoryEntity)

    suspend fun getAllCategory(): List<FoodCategory>

}