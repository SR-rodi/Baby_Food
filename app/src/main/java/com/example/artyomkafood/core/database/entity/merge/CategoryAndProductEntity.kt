package com.example.artyomkafood.core.database.entity.merge

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.artyomkafood.core.database.entity.CategoryEntity
import com.example.artyomkafood.core.database.entity.ProductEntity

@Entity(
    tableName = "category_product",
    primaryKeys = ["id_category", "id_product_food"],
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["category_id"],
            childColumns = ["id_category"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["product_id"],
            childColumns = ["id_product_food"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ]
)
class CategoryAndProductEntity(
    @ColumnInfo(name = "id_category")
    val categoryId: Int,
    @ColumnInfo(name = "id_product_food")
    val foodProductId: Int,
)