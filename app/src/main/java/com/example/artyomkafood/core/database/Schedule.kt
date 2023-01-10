package com.example.artyomkafood.core.database

import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.core.database.entity.ScheduleEntity

data class Schedule(
   val id: Int?,
   val name: String,
   val imageId: Int,
   var meal:MutableList<ScheduleMeal> = mutableListOf(),
) {
   fun toEntity()= ScheduleEntity(id, name, imageId)
}
