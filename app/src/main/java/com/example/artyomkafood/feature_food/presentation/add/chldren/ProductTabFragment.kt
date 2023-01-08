package com.example.artyomkafood.feature_food.presentation.add.chldren

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.databinding.FragmentFoodBinding
import com.example.artyomkafood.feature_food.domain.model.FoodProduct
import com.example.artyomkafood.feature_init.presentation.food.FoodAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductTabFragment() : BaseFragment<FragmentFoodBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentFoodBinding.inflate(inflater)

    private val viewModel by viewModel<ProductTabViewModel>()

    private var position = 0
    private var date = 0L
    private var scheduleId=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(POSITION)
            scheduleId = it.getInt(SCHEDULE_ID)
            date = it.getLong(DATE)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.startFragment(position)

        binding.addButton.setOnClickListener {
            viewModel.workDatabase(date,scheduleId)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collect { list ->
                binding.recyclerView.adapter =
                    FoodAdapter(list, {}) { onClickCheckBox(it) }
            }
        }
    }

    private fun onClickCheckBox(item: FoodProduct) {
        viewModel.addCheckList(item)
    }

    companion object {
        private const val POSITION = "position"
        private const val DATE = "date"
        private const val SCHEDULE_ID = "schedule_id"
        fun newInstance(param1: Int, param2: Long, param3: Int) =
            ProductTabFragment().apply {
                arguments = Bundle().apply {
                    putInt(POSITION, param1)
                    putLong(DATE, param2)
                    putInt(SCHEDULE_ID, param3)
                }
            }
    }
}