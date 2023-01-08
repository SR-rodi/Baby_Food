package com.example.artyomkafood.feature_food.presentation.day.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.OnSwipe
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.core.SwipeToDelete
import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.database.dao.ScheduleMeal
import com.example.artyomkafood.databinding.ItemMenuBinding
import com.example.artyomkafood.databinding.ItemScheduleBinding

class DayAdapter(
    private val list: List<Schedule>,
    private val onClickAddButton: (id: Int) -> Unit,
    private val onSwipeEvent: (scheduleMeal: ScheduleMeal) -> Unit,
) : RecyclerView.Adapter<MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MenuViewHolder(
        ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(list[position], { onClickAddButton(it) }) { onSwipeEvent(it) }
    }

    override fun getItemCount() = list.size

}

class MenuViewHolder(private val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(
        item: Schedule,
        onClickAddButton: (id: Int) -> Unit,
        onSwipeEvent: (scheduleMeal: ScheduleMeal) -> Unit,
    ) {

        var counter = 0
        item.meal.forEach {
            counter += it.meal_volume
        }

        val swipeItem = SwipeToDelete { position ->
            onSwipeEvent(item.meal[position])
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
            item.id?.let { it1 -> onClickAddButton(it1) }
        }

        binding.recyclerView.adapter = ScheduleMealAdapter(item.meal)


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

class ScheduleMealAdapter(private val list: List<ScheduleMeal>) :
    RecyclerView.Adapter<ScheduleMealViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ScheduleMealViewHolder(
        ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ScheduleMealViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

}

class ScheduleMealViewHolder(private val binding: ItemScheduleBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(item: ScheduleMeal) {
        binding.counter.text = item.meal_volume.toString() + "г"
        binding.name.text = item.product_name
    }
}