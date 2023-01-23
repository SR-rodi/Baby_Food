package com.example.artyomkafood.feature_food.data.reposytoryimpl

import com.example.artyomkafood.core.database.dao.ProductDao
import com.example.artyomkafood.core.database.entity.merge.CategoryAndProductEntity
import com.example.artyomkafood.core.database.entity.ProductEntity
import com.example.artyomkafood.core.extensions.toListFood
import com.example.artyomkafood.feature_food.domain.model.FoodProduct
import com.example.artyomkafood.feature_food.domain.repository.ProductRepository
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(private val dao: ProductDao): ProductRepository {

    override suspend fun addFood(item: ProductEntity)  = dao.insert(item)

    override suspend fun deleteFood(item: ProductEntity)  = dao.deleteItem(item)

    override suspend fun updateProduct(item: FoodProduct)  = dao.updateItem(item.toEntity())

    override suspend fun getAllFood() = dao.getAllProduct().toListFood()

    override fun getFoodByCategoryId(categoryId: Int) =
        dao.getFoodByCategoryID(categoryId).map { it.toListFood() }

    override suspend fun insertMergeProductInCategory(marge: CategoryAndProductEntity) =
        dao.insertMerge(marge)

    override suspend fun getLastIndex()=dao.getLastIndex()

}