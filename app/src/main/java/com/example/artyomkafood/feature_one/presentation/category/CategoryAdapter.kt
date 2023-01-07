package com.example.artyomkafood.feature_one.presentation.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.databinding.ItemCategoryBinding
import com.example.artyomkafood.feature_one.domain.model.FoodCategory

class CategoryAdapter(
    private val onClickItem: (id: Int?) -> Unit,
) : ListAdapter<FoodCategory, CategoryViewHolder>(CategoryDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryViewHolder(
        ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position)) { onClickItem(it) }
    }
}

class CategoryViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FoodCategory, onClickItem: (id: Int?) -> Unit) {
        binding.categoryName.text = item.categoryName
        binding.root.setOnClickListener {
            onClickItem(item.id)
        }
    }
}

class CategoryDiff() : DiffUtil.ItemCallback<FoodCategory>() {
    override fun areItemsTheSame(oldItem: FoodCategory, newItem: FoodCategory) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: FoodCategory, newItem: FoodCategory) =
        oldItem == newItem
}