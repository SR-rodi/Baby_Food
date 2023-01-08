package com.example.artyomkafood.feature_food.presentation.add.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.artyomkafood.feature_food.presentation.add.chldren.ProductTabFragment

class CategoryTabAdapter(
    fragment: Fragment,
    private val itemCount: Int,
    private val date: Long,
   private val scheduleId: Int,
) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = itemCount

    override fun createFragment(position: Int): Fragment {
        return ProductTabFragment.newInstance(position + 1,date,scheduleId)
    }
}