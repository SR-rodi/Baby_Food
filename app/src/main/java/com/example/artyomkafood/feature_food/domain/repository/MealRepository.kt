package com.example.artyomkafood.feature_food.domain.repository

import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.merge.ProductAndMealAndScheduleEntity
import com.example.artyomkafood.feature_food.domain.model.FoodMeal
import com.example.artyomkafood.feature_food.domain.model.FoodProduct

interface MealRepository {
    suspend fun insertMergeMeal(merge: ProductAndMealAndScheduleEntity)

    suspend fun getMealByProductId(id:Int):List<FoodMeal>

    suspend fun addMeal(meal: MealEntity)

    suspend fun addMealAndMerge(meal: MutableMap<Int, FoodProduct>, scheduleId: Int, date: Long)

    suspend fun getLastIndex():Int

    suspend fun delete(meal: MealEntity)

    suspend fun update(meal: MealEntity)
}