package com.example.artyomkafood.feature_food.data

import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.database.dao.ScheduleDao
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.core.extensions.toListEntity
import com.example.artyomkafood.core.extensions.toListSchedule
import com.example.artyomkafood.feature_food.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class ScheduleRepositoryImpl(
    private val dao: ScheduleDao,
) : ScheduleRepository {

    override suspend fun addListSchedule(list: List<Schedule>) = dao.insert(list.toListEntity())

    override fun getSchedule(date: Long) =
        dao.getSchedule().map { it.toListSchedule() }
            .combine(dao.getMeatByScheduleId(date), this::merge)

    private fun merge(
        schedules: List<Schedule>,
        mealSchedules: Map<String, MutableList<ScheduleMeal>>,
    ): List<Schedule> {
        return schedules.map { schedule->
            if (mealSchedules[schedule.name] != null) schedule.meal = mealSchedules[schedule.name]!!
            else schedule.meal = mutableListOf()
            schedule
        }
    }
}