package com.example.artyomkafood.di

import androidx.room.Room
import com.example.artyomkafood.core.database.AppDatabase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataBaseModule = module {
    single(named("Artyom")) {
        Room.databaseBuilder(
            get(), AppDatabase::class.java, "Artyom"
        ).build()
    }

    single {
        get<AppDatabase>(named("Artyom")).getCategoryDao()
    }

    single {
        get<AppDatabase>(named("Artyom")).getProductDao()
    }

    single {
        get<AppDatabase>(named("Artyom")).getMealDao()
    }

    single {
        get<AppDatabase>(named("Artyom")).getScheduleDao()
    }
}
