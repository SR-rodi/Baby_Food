package com.example.artyomkafood.feature_food.presentation.day.adapter.view_holder

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.core.ClickState
import com.example.artyomkafood.core.SwipeToDelete
import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.databinding.ItemMenuBinding
import com.example.artyomkafood.feature_food.presentation.day.adapter.ScheduleMealAdapter

class MenuViewHolder(private val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(
        item: Schedule,
        onEvent: (state: ClickState) -> Unit,
    ) {

        var counter = 0
        item.meal.forEach { counter += it.meal_volume }

        val swipeItem = SwipeToDelete { position ->
            onEvent(ClickState.SWIPE.apply {
                meal = item.meal[position]
            })
            counter -= item.meal[position].meal_volume

            item.meal.removeAt(position)
            binding.recyclerView.adapter?.notifyItemRemoved(position)

            binding.counterGr.text = counter.toString() + "гр"
            binding.expanded.isVisible = item.meal.isNotEmpty()
            binding.divider.isVisible = item.meal.isNotEmpty()
            binding.counter.text = item.meal.size.toString() + " шт"
        }


        val itemTouchHelper = ItemTouchHelper(swipeItem)

        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        binding.root.setOnClickListener {
            onEvent(ClickState.ADD_BUTTON.apply{
              meal = item.meal.first()
            })
        }
        val adapter = ScheduleMealAdapter { onEvent(it) }
        adapter.submitList(item.meal)
        binding.recyclerView.adapter = adapter

        binding.counterGr.text = counter.toString() + "гр"

        binding.expanded.isVisible = item.meal.isNotEmpty()
        binding.counter.text = item.meal.size.toString() + " шт"
        binding.title.text = item.name
        binding.icon.setImageResource(item.imageId)
        binding.expanded.setOnClickListener {
            binding.divider.isVisible = !binding.divider.isVisible
            binding.expanded.isSelected = !binding.expanded.isSelected
            binding.arrow.rotation = binding.arrow.rotation + 180
            binding.recyclerView.isVisible = !binding.recyclerView.isVisible
        }
    }
}