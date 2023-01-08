package com.example.artyomkafood.di

import com.example.artyomkafood.feature_food.presentation.add.AddViewModel
import com.example.artyomkafood.feature_food.presentation.add.chldren.ProductTabViewModel
import com.example.artyomkafood.feature_food.presentation.day.DayViewModel
import com.example.artyomkafood.feature_init.presentation.category.StartViewModel
import com.example.artyomkafood.feature_init.presentation.food.FoodViewModel
import com.example.artyomkafood.feature_init.presentation.meal.MealViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModel = module {

    viewModel { StartViewModel(get(), get(), get(), get()) }

    viewModel { FoodViewModel(get()) }

    viewModel { MealViewModel(get()) }

    viewModel { DayViewModel(get(),get()) }

    viewModel { AddViewModel(get()) }

    viewModel { ProductTabViewModel(get(),get()) }

}