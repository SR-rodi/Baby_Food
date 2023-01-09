package com.example.artyomkafood.feature_food.presentation.add

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.databinding.FragmentAddBinding
import com.example.artyomkafood.feature_food.domain.model.FoodCategory
import com.example.artyomkafood.feature_food.presentation.add.adapter.CategoryTabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFragment : BaseFragment<FragmentAddBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentAddBinding.inflate(inflater)

    private val viewModel by viewModel<AddViewModel>()

    private val args by navArgs<AddFragmentArgs>()

    private val adapter by lazy {
        CategoryTabAdapter(this@AddFragment) { position ->
            viewModel.settingChildren(position, args.date, args.scheduleId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCategory()

        dataObserver(viewModel.data) { list ->
            setAdapter(list)
            setTabLayout(list)
        }
    }

    private fun setAdapter(list: List<FoodCategory>) {
        adapter.setSize(list.size)
        binding.viewPager.adapter = adapter
    }

    private fun setTabLayout(list: List<FoodCategory>) =
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = list[position].categoryName
        }.attach()
}