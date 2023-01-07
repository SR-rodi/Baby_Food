package com.example.artyomkafood.core.database.dao

import androidx.room.*
import com.example.artyomkafood.core.database.entity.CategoryAndProductEntity
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.ProductAndMealEntity
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

/*SELECT "product"."product_name"
FROM "product"
INNER JOIN "category_product"
	ON "product"."product_id"  = "category_product"."product_id_i"
		AND "category_product"."category_id_i" = 1*/