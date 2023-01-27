package com.example.artyomkafood.feature_food.presentation.day.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.artyomkafood.core.basemodel.RecyclerViewListAdapter
import com.example.artyomkafood.databinding.ItemDateBinding
import com.example.artyomkafood.feature_food.presentation.day.adapter.view_holder.DateViewHolder

class DateAdapter(
    private val onClickDate: () -> Unit,
) : RecyclerViewListAdapter<Int, DateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DateViewHolder(
            ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(list[position]) { onClickDate() }
    }
}
