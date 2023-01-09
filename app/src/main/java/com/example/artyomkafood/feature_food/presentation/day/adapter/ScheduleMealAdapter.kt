package com.example.artyomkafood.feature_food.presentation.day.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.artyomkafood.core.basemodel.RecyclerViewListAdapter
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.databinding.ItemScheduleBinding
import com.example.artyomkafood.feature_food.presentation.day.adapter.view_holder.ScheduleMealViewHolder

class ScheduleMealAdapter (
    private val onClick:(item:ScheduleMeal)->Unit
        ): RecyclerViewListAdapter<ScheduleMeal, ScheduleMealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ScheduleMealViewHolder(
        ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ScheduleMealViewHolder, position: Int) {
        holder.bind(list[position]){ onClick(it) }
    }
}