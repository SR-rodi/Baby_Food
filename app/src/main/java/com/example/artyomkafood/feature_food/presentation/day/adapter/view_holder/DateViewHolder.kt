package com.example.artyomkafood.feature_food.presentation.day.adapter.view_holder

import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.databinding.ItemDateBinding

class DateViewHolder(private val binding: ItemDateBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Int, onClickDate: () -> Unit) {
        binding.root.setOnClickListener {
            onClickDate()
        }
        binding.date.text = item.toString()
    }
}