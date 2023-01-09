package com.example.artyomkafood.feature_food.presentation.day.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.core.basemodel.RecyclerViewListAdapter
import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.databinding.ItemMenuBinding
import com.example.artyomkafood.feature_food.presentation.day.adapter.view_holder.MenuViewHolder

class DayAdapter(
    private val onClickAddButton: (id: Int) -> Unit,
    private val onSwipeEvent: (scheduleMeal: ScheduleMeal) -> Unit,
    private val onClickFoodItem: (item: ScheduleMeal) -> Unit,
) : RecyclerViewListAdapter<Schedule, MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MenuViewHolder(
        ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(list[position], { onClickAddButton(it) },{ onSwipeEvent(it) }){onClickFoodItem(it)}
    }
}



