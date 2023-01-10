package com.example.artyomkafood.feature_food.domain.repository

import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    suspend fun addListSchedule(list: List<Schedule>)

    suspend fun getSchedule(date: Long): List<Schedule>

    suspend fun getMeal(date: Long): Flow<Map<String, MutableList<ScheduleMeal>>>
}