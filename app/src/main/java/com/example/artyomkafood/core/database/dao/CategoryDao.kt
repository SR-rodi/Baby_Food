package com.example.artyomkafood.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.artyomkafood.core.database.entity.CategoryEntity
import com.google.android.material.circularreveal.CircularRevealHelper.Strategy

@Dao
interface CategoryDao {

    @Query("SELECT*FROM category")
    fun getAllCategory(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: CategoryEntity)

    @Delete
    fun deleteItem(category: CategoryEntity)
}
