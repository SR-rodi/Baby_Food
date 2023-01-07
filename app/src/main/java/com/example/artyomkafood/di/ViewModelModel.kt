package com.example.artyomkafood.di

import com.example.artyomkafood.feature_one.presentation.category.StartViewModel
import com.example.artyomkafood.feature_one.presentation.food.FoodViewModel
import com.example.artyomkafood.feature_one.presentation.meal.MealViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModel = module {

    viewModel { StartViewModel(get(),get(),get()) }

    viewModel { FoodViewModel(get()) }

    viewModel { MealViewModel(get()) }

}