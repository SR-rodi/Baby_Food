package com.example.artyomkafood.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.ProductAndMealEntity
import com.example.artyomkafood.core.database.entity.ProductEntity

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMeal(meal: MealEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMergeMeal(merge: ProductAndMealEntity)

    @Query("SELECT*FROM meal Inner Join product_meal On meal_id = meal_id_merge " +
            "WHERE product_id_merge =:productId")
    fun getMealByProductId(productId: Int): List<MealEntity>
}