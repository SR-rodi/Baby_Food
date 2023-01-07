package com.example.artyomkafood.core

import com.example.artyomkafood.core.database.entity.CategoryAndProductEntity
import com.example.artyomkafood.core.database.entity.MealEntity
import com.example.artyomkafood.core.database.entity.ProductAndMealEntity
import java.util.Calendar

object InitDataBase {
    val categoryList = listOf("Овощи", "Фрукты", "Каша", "Мясо", "Молоко", "Другие")
    val productList =
        listOf("Кабачок",
            "Брокколи ",
            "Цветная капуста",
            "Рисовая каша",
            "Кукурузная каша",
            "Гречневая каша",
            "Яблочное пюре",
            "Индейка",
            "Кролик",
            "БитТворог Тёма",
            "Тыква",
            "Морковь")

    val mergeList = listOf(
        CategoryAndProductEntity(1, 1),
        CategoryAndProductEntity(1, 2),
        CategoryAndProductEntity(1, 3),
        CategoryAndProductEntity(2, 7),
        CategoryAndProductEntity(3, 4),
        CategoryAndProductEntity(3, 5),
        CategoryAndProductEntity(3, 6),
        CategoryAndProductEntity(4, 8),
        CategoryAndProductEntity(4, 9),
        CategoryAndProductEntity(5, 10),
        CategoryAndProductEntity(1, 12),
    )

    val mealList = listOf<MealEntity>(
        //кабачок
        MealEntity(2, getData(18, 10)),
        MealEntity(5, getData(19, 10)),
        MealEntity(10, getData(20, 10)),
        MealEntity(20, getData(21, 10)),
        MealEntity(40, getData(22, 10)),
        MealEntity(60, getData(23, 10)),
        MealEntity(80, getData(24, 10)),
        MealEntity(80, getData(25, 10)),
        MealEntity(80, getData(30, 11)),
        MealEntity(80, getData(5, 12)),
        MealEntity(80, getData(8, 12)),
        MealEntity(80, getData(11, 12)),
        MealEntity(80, getData(14, 12)),
        MealEntity(80, getData(15, 12)),
        MealEntity(80, getData(16, 12)),
        MealEntity(80, getData(21, 12)),
        MealEntity(80, getData(26, 12)),
        MealEntity(80, getData(27, 12)),
        MealEntity(80, getData(28, 12)),
        MealEntity(80, getData(29, 12)),
        MealEntity(80, getData(31, 12)),
        MealEntity(80, getData(1, 1,2023)),
        MealEntity(70, getData(2, 1,2023)),
        MealEntity(70, getData(3, 1,2023)),
        MealEntity(79, getData(4, 1,2023)),
        MealEntity(72, getData(5, 1,2023)),
        //Брокколи 25
        MealEntity(5, getData(29, 10)),
        MealEntity(10, getData(14, 12)),
        MealEntity(20, getData(15, 12)),
        MealEntity(30, getData(16, 12)),
        MealEntity(30, getData(21, 12)),
        MealEntity(40, getData(27, 12)),
        MealEntity(40, getData(28, 12)),
        MealEntity(50, getData(2, 1,2023)),
        //Цветная капуста 33
        MealEntity(5, getData(12, 11)),
        MealEntity(10, getData(5, 12)),
        MealEntity(20, getData(26, 12)),
        MealEntity(30, getData(29, 12)),
        MealEntity(40, getData(1, 1,23)),
        MealEntity(40, getData(3, 1,23)),
        MealEntity(50, getData(5, 1,23)),
        //Рисовая Каша 40
        MealEntity(5, getData(25, 10)),
        MealEntity(10, getData(26, 10)),
        MealEntity(20, getData(27, 10)),
        MealEntity(40, getData(28, 10)),
        MealEntity(60, getData(29, 10)),
        MealEntity(100, getData(31, 10)),
        MealEntity(60, getData(11, 11)),
        MealEntity(50, getData(17, 11)),
        MealEntity(50, getData(18, 11)),
        MealEntity(0, getData(19, 11)),
        MealEntity(40, getData(20, 11)),
        MealEntity(0, getData(8, 12)),
        MealEntity(0, getData(16, 12)),
        MealEntity(100, getData(26, 12)),
        MealEntity(115, getData(1, 1,2023)),
        //кукрузнвя каша 45
        MealEntity(5, getData(17, 11)),
        MealEntity(10, getData(18, 11)),
        MealEntity(20, getData(19, 11)),
        MealEntity(30, getData(30, 11)),
        MealEntity(35, getData(1, 12)),
        MealEntity(25, getData(2, 12)),
        MealEntity(45, getData(4, 12)),
        MealEntity(55, getData(5, 12)),
        MealEntity(0, getData(6, 12)),
        MealEntity(0, getData(13, 12)),
        //Гречневая каша 55
        MealEntity(5, getData(27, 12)),
        MealEntity(10, getData(28, 12)),
        //Яблочное пюре 57
        MealEntity(5, getData(16, 11)),
        MealEntity(10, getData(27, 11)),
        MealEntity(40, getData(25, 12)),
        MealEntity(50, getData(26, 12)),
        MealEntity(20, getData(27, 12)),
        MealEntity(20, getData(28, 12)),
        MealEntity(55, getData(5, 1,2023)),
        //индейка 64
        MealEntity(5, getData(1, 12)),
        MealEntity(10, getData(14, 12)),
        MealEntity(20, getData(15, 12)),
        MealEntity(30, getData(16, 12)),
        MealEntity(30, getData(26, 12)),
        MealEntity(40, getData(27, 12)),
        MealEntity(40, getData(28, 12)),
        MealEntity(40, getData(29, 12)),
        MealEntity(40, getData(31, 12)),
        MealEntity(50, getData(2, 1,2023)),
        MealEntity(50, getData(4, 1,2023)),
        //Кролие 75
        MealEntity(5, getData(21, 12)),
        MealEntity(40, getData(30, 12)),
        MealEntity(40, getData(1, 1,2023)),
        MealEntity(40, getData(3, 1,2023)),
        MealEntity(50, getData(5, 1,2023)),
        //биоТворог 80
        MealEntity(5, getData(25, 12)),
        MealEntity(12, getData(26, 12)),
        MealEntity(20, getData(27, 12)),
        MealEntity(35, getData(28, 12)),
        MealEntity(45, getData(5, 1,2023)),
        //Тыква85
        MealEntity(5, getData(27, 12)),
        //Морковка86
        MealEntity(5, getData(31, 12)),
        MealEntity(10, getData(2, 1,2023)),
        MealEntity(20, getData(3, 1,2023)),
        MealEntity(30, getData(4, 1,2023)),
        MealEntity(41, getData(5, 1,2023)),
    )

    fun mergeListMeal (): MutableList<ProductAndMealEntity> {
      val list= mutableListOf<ProductAndMealEntity>()
      for (i in 0..25){
          list.add(ProductAndMealEntity(1,i+1))
      }
      for(i in 26..33){
          list.add(ProductAndMealEntity(2,i+1))
      }
        for(i in 34..40){
            list.add(ProductAndMealEntity(3,i+1))
        }
        for(i in 41..45){
            list.add(ProductAndMealEntity(4,i+1))
        }
        for(i in 46..55){
            list.add(ProductAndMealEntity(5,i+1))
        }
        for(i in 56..57){
            list.add(ProductAndMealEntity(6,i+1))
        }
        for(i in 58..64){
            list.add(ProductAndMealEntity(7,i+1))
        }
        for(i in 65..75){
            list.add(ProductAndMealEntity(8,i+1))
        }
        for(i in 76..80){
            list.add(ProductAndMealEntity(9,i+1))
        }
        for(i in 81..85){
            list.add(ProductAndMealEntity(10,i+1))
        }
        list.add(ProductAndMealEntity(11,87))
        for(i in 87..91){
            list.add(ProductAndMealEntity(8,i+1))
        }
        return list
    }

    private fun getData(day: Int, month: Int,year:Int =2022): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.YEAR,year)
        return calendar.timeInMillis
    }
}