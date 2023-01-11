package com.example.artyomkafood.feature_food.domain.repository

import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    suspend fun addListSchedule(list: List<Schedule>)

    fun getSchedule(date: Long): Flow<List<Schedule>>

}