package com.example.artyomkafood.feature_food.presentation.day.adapter.view_holder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.core.ClickState
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.databinding.ItemScheduleBinding

class ScheduleMealViewHolder(private val binding: ItemScheduleBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(item: ScheduleMeal,onClickItem:(state:ClickState)->Unit) {
        binding.root.setOnClickListener {
            onClickItem(ClickState.CLICK_ITEM.apply{
                meal = item
            })
        }
        binding.counter.text = item.meal_volume.toString() + "г"
        binding.name.text = item.product_name
    }
}