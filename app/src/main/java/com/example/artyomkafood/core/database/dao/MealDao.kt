package com.example.artyomkafood.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.artyomkafood.core.database.entity.MealAndProduct
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.merge.ProductAndMealAndScheduleEntity

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMeal(meal: MealEntity)

    @Delete
    fun deleteMeal(meal: MealEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMergeMeal(merge: ProductAndMealAndScheduleEntity)

    @Query(
        "SELECT*FROM meal Inner Join product_meal On meal_id = meal_id_merge " +
                "WHERE product_id_merge =:productId"
    )
    fun getMealByProductId(productId: Int): List<MealEntity>

    @Query(
        "SELECT*, product_name FROM meal,product " +
                "Inner Join product_meal " +
                "On meal_id = meal_id_merge and product_id = product_id_merge"
    )
    fun getAllMeal(): List<MealAndProduct>

    @Query("SELECT seq FROM sqlite_sequence WHERE name=\"meal\"")
    fun getLastIndex(): Int

    @Update
    fun updateMeal(meal: MealEntity)
}