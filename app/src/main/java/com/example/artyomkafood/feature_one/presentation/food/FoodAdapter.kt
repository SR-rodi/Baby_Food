package com.example.artyomkafood.feature_one.presentation.food

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.databinding.ItemFoodBinding
import com.example.artyomkafood.feature_one.domain.model.FoodProduct

class FoodAdapter(
    private val listItems: List<FoodProduct>,
    private val onClickItem:(id:Int?)->Unit,
) : RecyclerView.Adapter<FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FoodViewHolder(
        ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(listItems[position]){onClickItem(it)}
    }

    override fun getItemCount() = listItems.size
}

class FoodViewHolder(private val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FoodProduct,onClickItem:(id:Int?)->Unit,) {
        binding.text.text = item.name
        binding.root.setOnClickListener{
            onClickItem(item.id)
        }
    }
}