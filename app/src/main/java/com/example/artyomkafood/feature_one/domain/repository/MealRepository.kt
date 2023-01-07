package com.example.artyomkafood.feature_one.domain.repository

import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.ProductAndMealEntity
import com.example.artyomkafood.feature_one.domain.model.FoodMeal

interface MealRepository {
    suspend fun insertMergeMeal(merge: ProductAndMealEntity)

    suspend fun getMealByProductId(id:Int):List<FoodMeal>

    suspend fun addMeal(meal: MealEntity)
}