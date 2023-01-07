package com.example.artyomkafood.feature_one.data

import com.example.artyomkafood.core.database.dao.MealDao
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.ProductAndMealEntity
import com.example.artyomkafood.core.extensions.toListFoodMeal
import com.example.artyomkafood.feature_one.domain.repository.MealRepository

class MealRepositoryImpl(private val dao: MealDao) : MealRepository {
    override suspend fun insertMergeMeal(merge: ProductAndMealEntity) = dao.insertMergeMeal(merge)

    override suspend fun getMealByProductId(id: Int) = dao.getMealByProductId(id).toListFoodMeal()

    override suspend fun addMeal(meal: MealEntity) = dao.insertMeal(meal)
}