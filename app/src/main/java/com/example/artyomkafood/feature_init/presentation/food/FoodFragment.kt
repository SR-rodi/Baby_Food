package com.example.artyomkafood.feature_init.presentation.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.databinding.FragmentFoodBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FoodFragment : BaseFragment<FragmentFoodBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFoodBinding.inflate(inflater)

    private val args by navArgs<FoodFragmentArgs>()

    private val viewModel by viewModel<FoodViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFood(args.categoryId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collect { foodList ->
               // binding.recyclerView.adapter = FoodAdapter(foodList){navigate(it)}
            }
        }

    }

    private fun navigate(id: Int?) {
        if (id!=null)
            findNavController().navigate(FoodFragmentDirections.actionFoodFragmentToMealFragment(id))
    }
}