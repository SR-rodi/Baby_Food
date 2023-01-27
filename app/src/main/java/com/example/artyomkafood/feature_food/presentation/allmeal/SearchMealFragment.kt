package com.example.artyomkafood.feature_food.presentation.allmeal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.core.database.entity.MealAndProduct
import com.example.artyomkafood.databinding.FragmentSearchMealBinding
import com.example.artyomkafood.feature_food.presentation.allmeal.adapter.SearchAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchMealFragment : BaseFragment<FragmentSearchMealBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentSearchMealBinding.inflate(inflater)

    private val viewModel by viewModel<SearchMealViewModel>()

    private val searchAdapter by lazy { SearchAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.doOnTextChanged { text, _, _, _ ->
            viewModel.setQuery(text.toString())
        }

       dataObserver(viewModel.data) { mealList -> setAdapter(mealList) }
    }

    private fun setAdapter(mealList: List<MealAndProduct>) {
        searchAdapter.submitList(mealList)
        binding.recyclerView.adapter = searchAdapter
    }
}