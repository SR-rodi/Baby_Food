package com.example.artyomkafood.di

import androidx.lifecycle.SavedStateHandle
import com.example.artyomkafood.feature_food.data.CategoryRepositoryImpl
import com.example.artyomkafood.feature_food.data.MealRepositoryImpl
import com.example.artyomkafood.feature_food.data.ProductRepositoryImpl
import com.example.artyomkafood.feature_food.data.ScheduleRepositoryImpl
import com.example.artyomkafood.feature_food.domain.repository.CategoryRepository
import com.example.artyomkafood.feature_food.domain.repository.MealRepository
import com.example.artyomkafood.feature_food.domain.repository.ProductRepository
import com.example.artyomkafood.feature_food.domain.repository.ScheduleRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }

    single<ProductRepository> { ProductRepositoryImpl(get()) }

    single<MealRepository> { MealRepositoryImpl(get()) }

    single<ScheduleRepository> { ScheduleRepositoryImpl(get()) }

    single { SavedStateHandle() }
}