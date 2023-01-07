package com.example.artyomkafood.di

import com.example.artyomkafood.feature_one.data.CategoryRepositoryImpl
import com.example.artyomkafood.feature_one.data.MealRepositoryImpl
import com.example.artyomkafood.feature_one.data.ProductRepositoryImpl
import com.example.artyomkafood.feature_one.domain.repository.CategoryRepository
import com.example.artyomkafood.feature_one.domain.repository.MealRepository
import com.example.artyomkafood.feature_one.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }

    single<ProductRepository> { ProductRepositoryImpl(get()) }

    single<MealRepository> { MealRepositoryImpl(get()) }
}