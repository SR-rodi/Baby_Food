package com.example.artyomkafood.feature_init.presentation.food

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.databinding.ItemFoodBinding
import com.example.artyomkafood.feature_food.domain.model.FoodMeal
import com.example.artyomkafood.feature_food.domain.model.FoodProduct

class FoodAdapter(
    private val listItems: List<FoodProduct>,
    private val onClickItem: (id: Int?) -> Unit,
    private val onClickCheckBox: (item: FoodProduct) -> Unit,
) : RecyclerView.Adapter<FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FoodViewHolder(
        ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(listItems[position], { onClickItem(it) }) { onClickCheckBox(it) }
    }

    override fun getItemCount() = listItems.size
}

class FoodViewHolder(private val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: FoodProduct,
        onClickItem: (id: Int?) -> Unit,
        onClickCheckBox: (item: FoodProduct) -> Unit,
    ) {
        binding.name.text = item.name
        binding.counter.text = item.volume.toString()
        binding.checkbox.setOnClickListener {
            onClickCheckBox(item)
        }
        binding.root.setOnClickListener {
            onClickItem(item.id)
        }
    }
}