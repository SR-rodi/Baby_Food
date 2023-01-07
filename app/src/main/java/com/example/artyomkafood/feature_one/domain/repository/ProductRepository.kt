package com.example.artyomkafood.feature_one.domain.repository

import com.example.artyomkafood.core.database.entity.CategoryAndProductEntity
import com.example.artyomkafood.core.database.entity.ProductAndMealEntity
import com.example.artyomkafood.core.database.entity.ProductEntity
import com.example.artyomkafood.feature_one.domain.model.FoodCategory
import com.example.artyomkafood.feature_one.domain.model.FoodMeal
import com.example.artyomkafood.feature_one.domain.model.FoodProduct

interface ProductRepository {

    suspend fun addFood(item: ProductEntity)

    suspend fun deleteFood(item: ProductEntity)

    suspend fun getAllFood(): List<FoodProduct>

    suspend fun getFoodByCategoryId(categoryId: Int): List<FoodProduct>

    suspend fun insertMergeProductInCategory(marge: CategoryAndProductEntity)

}