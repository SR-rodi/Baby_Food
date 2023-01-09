package com.example.artyomkafood.feature_init.presentation.food

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.core.basemodel.RecyclerViewListAdapter
import com.example.artyomkafood.databinding.ItemFoodBinding
import com.example.artyomkafood.feature_food.domain.model.FoodMeal
import com.example.artyomkafood.feature_food.domain.model.FoodProduct
import com.example.artyomkafood.feature_food.presentation.add.adapter.FoodViewHolder

class FoodAdapter(
    private val onClickItem: (id: Int?) -> Unit,
    private val onClickCheckBox: (item: FoodProduct) -> Unit,
) : RecyclerViewListAdapter<FoodProduct, FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FoodViewHolder(
        ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(list[position], { onClickItem(it) }) { onClickCheckBox(it) }
    }

}