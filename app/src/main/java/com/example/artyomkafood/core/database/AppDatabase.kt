package com.example.artyomkafood.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.artyomkafood.core.database.dao.CategoryDao
import com.example.artyomkafood.core.database.dao.MealDao
import com.example.artyomkafood.core.database.dao.ProductDao
import com.example.artyomkafood.core.database.entity.*

@Database(entities = [
    CategoryAndProductEntity::class,
    ProductAndMealEntity::class,
    CategoryEntity::class,
    ProductEntity::class,
    MealEntity::class,
], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao

    abstract fun getProductDao(): ProductDao

    abstract fun getMealDao(): MealDao
}