package com.example.artyomkafood.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.ScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: List<ScheduleEntity>)

    @Query("SELECT*from schedule")
    fun getSchedule(): List<ScheduleEntity>

    @Query("SELECT meal_volume, meal_data,meal_id, product_name,schedule_id_merge " +
            "From meal,product" +
            " inner join product_meal " +
            "on meal_id = meal_id_merge  " +
            "and product_id = product_id_merge Where meal_data =:date")
    fun getMeatByScheduleId(date: Long): List<ScheduleMeal>
}

class ScheduleMeal(
    val product_name: String,
    val meal_volume: Int,
    val meal_data: Long,
    val meal_id: Int,
    val schedule_id_merge: Int,

    )