package com.example.artyomkafood.feature_food.presentation.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.databinding.FragmentAddBinding
import com.example.artyomkafood.feature_food.presentation.add.adapter.CategoryTabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFragment : BaseFragment<FragmentAddBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentAddBinding.inflate(inflater)

    private val viewModel by viewModel<AddViewModel>()

    private val args by navArgs<AddFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCategory()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collect { list ->

                binding.viewPager.adapter =
                    CategoryTabAdapter(this@AddFragment, list.size, args.date,args.scheduleId)

                TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                    tab.text = list[position].categoryName
                }.attach()
            }
        }
    }

}