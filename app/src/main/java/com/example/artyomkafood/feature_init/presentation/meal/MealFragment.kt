package com.example.artyomkafood.feature_init.presentation.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.databinding.FragmentMealBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MealFragment : BaseFragment<FragmentMealBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentMealBinding.inflate(inflater)

    private val viewModel by viewModel<MealViewModel>()

    private val args by navArgs<MealFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData(args.productId)

        viewLifecycleOwner.lifecycleScope.launch{
           viewModel.data.collect{ listMeat->
               binding.recyclerView.adapter = MealAdapter(listMeat)
           }
        }
    }

}