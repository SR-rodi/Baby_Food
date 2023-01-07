package com.example.artyomkafood.feature_one.data

import com.example.artyomkafood.core.database.dao.CategoryDao
import com.example.artyomkafood.core.database.entity.CategoryEntity
import com.example.artyomkafood.core.extensions.toListCategory
import com.example.artyomkafood.feature_one.domain.repository.CategoryRepository

class CategoryRepositoryImpl(private val dao: CategoryDao) : CategoryRepository {
    override suspend fun addCategory(category: CategoryEntity) = dao.insert(category)

    override suspend fun deleteCategory(category: CategoryEntity) = dao.deleteItem(category)

    override suspend fun getAllCategory() = dao.getAllCategory().toListCategory()
}