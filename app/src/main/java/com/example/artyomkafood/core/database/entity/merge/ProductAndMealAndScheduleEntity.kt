package com.example.artyomkafood.core.database.entity.merge

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.ProductEntity

@Entity(
    tableName = "product_meal",
    primaryKeys = ["product_id_merge", "meal_id_merge"],
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["product_id"],
            childColumns = ["product_id_merge"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MealEntity::class,
            parentColumns = ["meal_id"],
            childColumns = ["meal_id_merge"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ]
)
class ProductAndMealAndScheduleEntity(
    @ColumnInfo(name = "product_id_merge")
    val productId: Int,
    @ColumnInfo(name = "meal_id_merge")
    val mealId: Int,
    @ColumnInfo(name = "schedule_id_merge")
    val schedule: Int,
)