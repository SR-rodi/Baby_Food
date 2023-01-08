package com.example.artyomkafood.core.database

import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.core.database.entity.ScheduleEntity

class Schedule(
   val id: Int?,
   val name: String,
   val imageId: Int,
   val meal:MutableList<ScheduleMeal> = mutableListOf()
) {
   fun toEntity()= ScheduleEntity(id, name, imageId)
}
