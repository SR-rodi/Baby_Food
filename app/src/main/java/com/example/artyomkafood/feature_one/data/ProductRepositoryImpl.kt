package com.example.artyomkafood.feature_one.data

import com.example.artyomkafood.core.database.dao.ProductDao
import com.example.artyomkafood.core.database.entity.CategoryAndProductEntity
import com.example.artyomkafood.core.database.entity.ProductAndMealEntity
import com.example.artyomkafood.core.database.entity.ProductEntity
import com.example.artyomkafood.core.extensions.toListFood
import com.example.artyomkafood.core.extensions.toListFoodMeal
import com.example.artyomkafood.feature_one.domain.model.FoodProduct
import com.example.artyomkafood.feature_one.domain.repository.ProductRepository

class ProductRepositoryImpl(private val dao: ProductDao):ProductRepository {

    override suspend fun addFood(item: ProductEntity)  = dao.insert(item)

    override suspend fun deleteFood(item: ProductEntity)  = dao.deleteItem(item)

    override suspend fun getAllFood() = dao.getAllProduct().toListFood()

    override suspend fun getFoodByCategoryId(categoryId: Int) =
        dao.getFoodByCategoryID(categoryId).toListFood()

    override suspend fun insertMergeProductInCategory(marge: CategoryAndProductEntity) =
        dao.insertMerge(marge)

}