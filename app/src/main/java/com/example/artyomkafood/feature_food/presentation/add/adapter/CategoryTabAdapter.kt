package com.example.artyomkafood.feature_food.presentation.add.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.artyomkafood.feature_food.presentation.add.chldren.ProductTabFragment

class CategoryTabAdapter(
    fragment: Fragment,
    private val onSetting: (position: Int) -> Unit,
) : FragmentStateAdapter(fragment) {

    fun setSize(count: Int) {
        itemsSize = count
    }

    private var itemsSize = 0

    override fun getItemCount() = itemsSize

    override fun createFragment(position: Int): Fragment {
        onSetting(position + 1)
        return ProductTabFragment()
    }
}