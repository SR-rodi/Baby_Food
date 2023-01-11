package com.example.artyomkafood.feature_food.presentation.day.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.core.basemodel.RecyclerViewListAdapter
import com.example.artyomkafood.databinding.ItemDateBinding

class DateAdapter : RecyclerViewListAdapter<Int, DateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DateViewHolder(
        ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(list[position])
    }
}

class DateViewHolder(private val binding: ItemDateBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Int) {
        binding.date.text = item.toString()
    }
}