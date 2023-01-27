package com.example.artyomkafood.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.artyomkafood.feature_food.domain.model.FoodCategory

@Entity(tableName = "category")
class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val id: Int? = null,
    @ColumnInfo(name = "category_name")
    val name: String,
) {
    fun toFoodCategory() = FoodCategory(id, name)
}