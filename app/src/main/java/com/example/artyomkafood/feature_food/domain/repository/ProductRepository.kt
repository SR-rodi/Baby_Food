package com.example.artyomkafood.feature_food.domain.repository

import com.example.artyomkafood.core.database.entity.merge.CategoryAndProductEntity
import com.example.artyomkafood.core.database.entity.ProductEntity
import com.example.artyomkafood.feature_food.domain.model.FoodProduct
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun addFood(item: ProductEntity)

    suspend fun deleteFood(item: ProductEntity)

    suspend fun updateProduct(item: FoodProduct)

    suspend fun getAllFood(): List<FoodProduct>

    fun getFoodByCategoryId(categoryId: Int): Flow<List<FoodProduct>>

    suspend fun insertMergeProductInCategory(marge: CategoryAndProductEntity)

    suspend fun getLastIndex():Int

}