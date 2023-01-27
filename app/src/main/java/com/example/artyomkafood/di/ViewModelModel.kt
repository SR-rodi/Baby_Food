package com.example.artyomkafood.di

import com.example.artyomkafood.feature_food.presentation.add.parent.AddViewModel
import com.example.artyomkafood.feature_food.presentation.add.chldren.ProductTabViewModel
import com.example.artyomkafood.feature_food.presentation.day.viewmodel.DayViewModel
import com.example.artyomkafood.feature_food.presentation.init.StartViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModel = module {

    viewModel { StartViewModel(get(), get(), get(), get()) }

    viewModel { DayViewModel(get(), get(), get()) }

    viewModel { AddViewModel(get(), get(), get()) }

    viewModel { ProductTabViewModel(get(), get(), get()) }

}