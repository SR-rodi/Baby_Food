package com.example.artyomkafood.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.artyomkafood.core.database.Schedule

@Entity(tableName = "schedule")
class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "schedule_id")
    val id: Int? = null,
    @ColumnInfo(name = "schedule_name")
    val name: String,
    @ColumnInfo(name="image_id")
    val imageId:Int,
){
    fun toSchedule() = Schedule(id,name,imageId)
}