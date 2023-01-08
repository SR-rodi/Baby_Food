package com.example.artyomkafood.core.database.dao

import androidx.room.*
import com.example.artyomkafood.core.database.entity.merge.CategoryAndProductEntity
import com.example.artyomkafood.core.database.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT*FROM product")
    fun getAllProduct(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(productEntity: ProductEntity)

    @Delete
    fun deleteItem(productEntity: ProductEntity)

    @Query("SELECT*FROM product Inner Join category_product On product_id = id_product_food " +
            "WHERE id_category =:categoryID ")
    fun getFoodByCategoryID(categoryID: Int): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMerge(merge: CategoryAndProductEntity)


}