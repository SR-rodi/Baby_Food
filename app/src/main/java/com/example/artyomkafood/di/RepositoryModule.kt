package com.example.artyomkafood.di

import androidx.lifecycle.SavedStateHandle
import com.example.artyomkafood.feature_food.data.reposytoryimpl.CategoryRepositoryImpl
import com.example.artyomkafood.feature_food.data.reposytoryimpl.MealRepositoryImpl
import com.example.artyomkafood.feature_food.data.reposytoryimpl.ProductRepositoryImpl
import com.example.artyomkafood.feature_food.data.reposytoryimpl.ScheduleRepositoryImpl
import com.example.artyomkafood.feature_food.data.calendar.CalendarRepositoryImpl
import com.example.artyomkafood.feature_food.domain.repository.*
import org.koin.dsl.module
import java.util.Calendar

val repositoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }

    single<ProductRepository> { ProductRepositoryImpl(get()) }

    single<MealRepository> { MealRepositoryImpl(get()) }

    single<ScheduleRepository> { ScheduleRepositoryImpl(get()) }

    single { SavedStateHandle() }

    single<CalendarRepository> { CalendarRepositoryImpl(get()) }
}

val calendarModule = module {
    single<Calendar> { Calendar.getInstance() }
}