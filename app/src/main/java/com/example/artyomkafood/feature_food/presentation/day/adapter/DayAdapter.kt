package com.example.artyomkafood.feature_food.presentation.day.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.core.ClickState
import com.example.artyomkafood.core.basemodel.RecyclerViewListAdapter
import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.databinding.ItemMenuBinding
import com.example.artyomkafood.feature_food.domain.model.FoodMeal
import com.example.artyomkafood.feature_food.presentation.day.adapter.view_holder.MenuViewHolder
import com.example.artyomkafood.feature_init.presentation.food.FoodAdapter

class DayAdapter(
    private val onEvent: (scheduleMeal: ClickState) -> Unit,
) : RecyclerViewListAdapter<Schedule, MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MenuViewHolder(
        ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(list[position]) { onEvent(it) }
    }
}