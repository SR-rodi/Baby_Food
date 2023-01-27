package com.example.artyomkafood.core.database.entity

import android.os.storage.StorageVolume
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.artyomkafood.feature_food.domain.model.FoodProduct

@Entity(tableName = "product")
class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    val id: Int? = null,
    @ColumnInfo(name = "product_name")
    val name: String,
    @ColumnInfo(name = "last_volume")
    val volume: Int = 40,
) {
    fun toFoodProduct() = FoodProduct(id, name, volume)
}