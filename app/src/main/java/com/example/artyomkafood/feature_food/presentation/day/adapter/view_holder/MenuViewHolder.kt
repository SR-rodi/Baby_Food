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

    private fun onSwipeItem(
        item: Schedule,
        onEvent: (state: ClickState) -> Unit,
    ): SwipeToDelete {
        return SwipeToDelete { position ->
            onEvent(ClickState.SWIPE.apply {
                meal = item.meal[position]
            })
        }
    }

    private fun onClickAddButton(
        item: Schedule,
        onEvent: (state: ClickState) -> Unit,
    ) {
        binding.root.setOnClickListener {
            onEvent(ClickState.ADD_BUTTON.apply {
                index = item.id
            })
        }
    }

    private fun onClickExpanded(
        item: Schedule,
        onEvent: (state: ClickState) -> Unit,
    ) {
        binding.expanded.setOnClickListener {
            binding.arrow.rotation = binding.arrow.rotation + 180

            binding.divider.isVisible = !binding.divider.isVisible
            binding.expanded.isSelected = !binding.expanded.isSelected
            binding.recyclerView.isVisible = !binding.recyclerView.isVisible
        }
    }

    @SuppressLint("SetTextI18n")
    fun bind(
        item: Schedule,
        onEvent: (state: ClickState) -> Unit,
    ) {

        var counter = 0
        item.meal.forEach { counter += it.meal_volume }

        ItemTouchHelper(onSwipeItem(item, onEvent)).attachToRecyclerView(binding.recyclerView)

        onClickAddButton(item, onEvent)

        onClickExpanded(item,onEvent)

        val adapter = ScheduleMealAdapter { onEvent(it) }
        adapter.submitList(item.meal)
        binding.recyclerView.adapter = adapter

        binding.counterGr.text = counter.toString() + "гр"

        binding.expanded.isVisible = item.meal.isNotEmpty()
        binding.divider.isVisible = item.meal.isNotEmpty()
        binding.expanded.isSelected = item.meal.isNotEmpty()
        binding.recyclerView.isVisible = item.meal.isNotEmpty()
        binding.counter.text = item.meal.size.toString() + " шт"
        binding.title.text = item.name
        binding.icon.setImageResource(item.imageId)

    }
}