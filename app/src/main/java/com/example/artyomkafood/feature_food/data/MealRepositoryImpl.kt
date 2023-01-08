package com.example.artyomkafood.feature_food.data

import com.example.artyomkafood.core.database.dao.MealDao
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.merge.ProductAndMealAndScheduleEntity
import com.example.artyomkafood.core.extensions.toListFoodMeal
import com.example.artyomkafood.feature_food.domain.repository.MealRepository

class MealRepositoryImpl(private val dao: MealDao) : MealRepository {
    override suspend fun insertMergeMeal(merge: ProductAndMealAndScheduleEntity) = dao.insertMergeMeal(merge)

    override suspend fun getMealByProductId(id: Int) = dao.getMealByProductId(id).toListFoodMeal()

    override suspend fun addMeal(meal: MealEntity) = dao.insertMeal(meal)

    override suspend fun getLastIndex() =dao.getLastIndex()

    override suspend fun delete(meal: MealEntity)= dao.deleteMeal(meal)
}