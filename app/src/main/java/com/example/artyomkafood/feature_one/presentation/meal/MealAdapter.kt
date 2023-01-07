package com.example.artyomkafood.feature_one.presentation.meal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.databinding.FragmentMealBinding
import com.example.artyomkafood.databinding.ItemMealBinding
import com.example.artyomkafood.feature_one.domain.model.FoodMeal
import java.text.SimpleDateFormat

class MealAdapter(
    private val list: List<FoodMeal>,
) : RecyclerView.Adapter<MealViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MealViewHolder(
        ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}

class MealViewHolder(private val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("dd.MM.yyyy")

    fun bind(item: FoodMeal) {
        binding.volume.text = item.volume.toString()
        binding.date.text = sdf.format(item.data)
    }
}