package com.example.artyomkafood.feature_food.data

import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.database.dao.ScheduleDao
import com.example.artyomkafood.core.extensions.toListEntity
import com.example.artyomkafood.core.extensions.toListSchedule
import com.example.artyomkafood.feature_food.domain.repository.ScheduleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class ScheduleRepositoryImpl(
    private val dao: ScheduleDao,
) : ScheduleRepository {

    override suspend fun addListSchedule(list: List<Schedule>) = dao.insert(list.toListEntity())


    override suspend fun getSchedule(date: Long): List<Schedule> {
        return  dao.getSchedule().toListSchedule().sortedBy {it.id}
 /*       val meal = getMeal(date)
        meal.forEach { scheduleMeal ->
            schedule[scheduleMeal.schedule_id_merge - 1].meal.add(scheduleMeal)
        }
        return schedule*/
    }

    override suspend fun getMeal(date: Long) = dao.getMeatByScheduleId(date)

}