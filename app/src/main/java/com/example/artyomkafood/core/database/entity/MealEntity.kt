package com.example.artyomkafood.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.artyomkafood.feature_food.domain.model.FoodMeal

@Entity(tableName = "meal")
class MealEntity(
    @ColumnInfo(name = "meal_volume")
    val volume: Int,
    @ColumnInfo(name = "meal_data")
    val data: Long,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "meal_id")
    val id: Int? = null,
    @ColumnInfo(name = "is_all")
    val isAll: Boolean = true,
) {
    fun toFoodMeal() = FoodMeal(volume, data, id, isAll)
}