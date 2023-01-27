package com.example.artyomkafood.feature_food.domain.model

import com.example.artyomkafood.core.database.entity.ProductEntity

class FoodProduct(
    val id: Int?,
    val name: String,
    var volume: Int,
) {
    fun toEntity() = ProductEntity(id, name, volume)
}