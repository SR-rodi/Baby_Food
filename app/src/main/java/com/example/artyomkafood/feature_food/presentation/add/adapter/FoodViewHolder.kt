package com.example.artyomkafood.feature_food.presentation.add.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.databinding.ItemFoodBinding
import com.example.artyomkafood.feature_food.domain.model.FoodProduct

class FoodViewHolder(private val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: FoodProduct,
        onClickItem: (item: FoodProduct) -> Unit,
        onClickCheckBox: (item: FoodProduct) -> Unit,
    ) {
        binding.name.text = item.name
        binding.counter.text = item.volume.toString()
        binding.checkbox.setOnClickListener {
            onClickCheckBox(item)
        }
        binding.root.setOnClickListener {
            onClickItem(item)
        }
    }
}