package com.example.artyomkafood.feature_food.presentation.allmeal.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artyomkafood.core.basemodel.RecyclerViewListAdapter
import com.example.artyomkafood.core.database.entity.MealAndProduct
import com.example.artyomkafood.databinding.ItemProductAndMealBinding
import java.text.SimpleDateFormat

class SearchAdapter : RecyclerViewListAdapter<MealAndProduct, SearchMealViewHolder>() {

    @SuppressLint("SimpleDateFormat")
    private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("dd.MM.yy")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchMealViewHolder(
        ItemProductAndMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SearchMealViewHolder, position: Int) {
        holder.bind(list[position], simpleDateFormat)
    }
}

class SearchMealViewHolder(private val binding: ItemProductAndMealBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MealAndProduct, simpleDateFormat: SimpleDateFormat) {

        binding.date.text = simpleDateFormat.format(item.data)
        binding.volume.text = item.volume.toString()
        binding.name.text = item.productName
    }
}