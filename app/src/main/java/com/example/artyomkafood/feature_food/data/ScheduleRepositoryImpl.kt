package com.example.artyomkafood.feature_food.data

import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.database.dao.ScheduleDao
import com.example.artyomkafood.core.extensions.toListEntity
import com.example.artyomkafood.core.extensions.toListSchedule
import com.example.artyomkafood.feature_food.domain.repository.ScheduleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class ScheduleRepositoryImpl(
    private val dao: ScheduleDao,
) : ScheduleRepository {

    override suspend fun addListSchedule(list: List<Schedule>) = dao.insert(list.toListEntity())


    override suspend fun getSchedule() = dao.getSchedule().toListSchedule()

    override suspend fun getMeal(data: Long) = dao.getMeatByScheduleId(data)

}