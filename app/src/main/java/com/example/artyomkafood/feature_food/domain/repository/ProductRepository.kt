package com.example.artyomkafood.feature_food.domain.repository

import com.example.artyomkafood.core.database.entity.merge.CategoryAndProductEntity
import com.example.artyomkafood.core.database.entity.ProductEntity
import com.example.artyomkafood.feature_food.domain.model.FoodProduct

interface ProductRepository {

    suspend fun addFood(item: ProductEntity)

    suspend fun deleteFood(item: ProductEntity)

    suspend fun getAllFood(): List<FoodProduct>

    suspend fun getFoodByCategoryId(categoryId: Int): List<FoodProduct>

    suspend fun insertMergeProductInCategory(marge: CategoryAndProductEntity)

}