package com.example.artyomkafood.core.database.dao

import android.os.Parcelable
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapInfo
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.ScheduleEntity
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: List<ScheduleEntity>)

    @Query("SELECT*from schedule")
    fun getSchedule(): Flow<List<ScheduleEntity>>

    @Query(
        "SELECT meal_volume, meal_data,meal_id, product_name,schedule_id_merge,schedule_name " +
                "From meal,product,schedule" +
                " inner join product_meal " +
                "on meal_id = meal_id_merge And schedule_id = schedule_id_merge " +
                "and product_id = product_id_merge Where meal_data =:date"
    )
    @MapInfo(keyColumn = "schedule_name")
    fun getMeatByScheduleId(date: Long): Flow<Map<String, MutableList<ScheduleMeal>>>

}

@Parcelize
data class ScheduleMeal(
    val product_name: String,
    var meal_volume: Int,
    val meal_data: Long,
    val meal_id: Int,
    val schedule_id_merge: Int,
    val schedule_name: String,
) : Parcelable