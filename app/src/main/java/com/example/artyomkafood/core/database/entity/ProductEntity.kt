package com.example.artyomkafood.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.artyomkafood.feature_one.domain.model.FoodProduct

@Entity(tableName = "product")
class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    val id:Int?=null,
    @ColumnInfo(name = "product_name")
    val name:String
){
    fun toFoodProduct() = FoodProduct(id, name)
}