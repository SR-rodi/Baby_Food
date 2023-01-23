package com.example.artyomkafood.feature_food.presentation.add.parent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.artyomkafood.core.basemodel.BaseViewModel
import com.example.artyomkafood.core.database.entity.ProductEntity
import com.example.artyomkafood.core.database.entity.merge.CategoryAndProductEntity
import com.example.artyomkafood.core.settings.SettingsCategoryAdapter
import com.example.artyomkafood.feature_food.domain.model.FoodCategory
import com.example.artyomkafood.feature_food.domain.repository.CategoryRepository
import com.example.artyomkafood.feature_food.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel(
    private val categoryRepository: CategoryRepository,
    private val savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository,
) : BaseViewModel<FoodCategory>() {

    val dropItems = mutableListOf<String>()

    fun getCategory() =
        viewModelScope.launch(Dispatchers.IO) {
            val categoryList = categoryRepository.getAllCategory()
            if (dropItems.isEmpty())
                categoryList.forEach { dropItems.add(it.categoryName) }
            _data.emit(categoryList)
        }

    fun addProduct(name: String,categoryId:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.addFood(ProductEntity(name = name))
            val lastIndex = productRepository.getLastIndex()
            productRepository.insertMergeProductInCategory(CategoryAndProductEntity(categoryId,lastIndex))
        }
    }

    fun settingChildren(position: Int, date: Long, scheduleId: Int) {
        savedStateHandle[SETTING_CHILDREN_FRAGMENT] =
            SettingsCategoryAdapter(date, scheduleId, position)
    }

    companion object {
        const val SETTING_CHILDREN_FRAGMENT = "settingChildren"
    }
}