package com.example.artyomkafood.core.extensions

import com.example.artyomkafood.core.database.entity.CategoryEntity
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.ProductEntity

fun List<CategoryEntity>.toListCategory() = this.map { entity -> entity.toFoodCategory() }

fun List<ProductEntity>.toListFood() = this.map { entity -> entity.toFoodProduct() }

fun List<MealEntity>.toListFoodMeal() = this.map { entity -> entity.toFoodMeal() }