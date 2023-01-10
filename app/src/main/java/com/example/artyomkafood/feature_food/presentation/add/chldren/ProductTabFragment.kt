package com.example.artyomkafood.feature_food.presentation.add.chldren

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.core.extensions.createEditDialog
import com.example.artyomkafood.databinding.FragmentFoodBinding
import com.example.artyomkafood.feature_food.domain.model.FoodProduct
import com.example.artyomkafood.feature_food.presentation.add.adapter.FoodAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductTabFragment() : BaseFragment<FragmentFoodBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentFoodBinding.inflate(inflater)

    private val viewModel by viewModel<ProductTabViewModel>()

    private val adapter by lazy { FoodAdapter({ product->
        createEditDialog(product.name,product.volume){ newValue->
            viewModel.updateProduct(product,newValue)
        }
    }) { onClickCheckBox(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.startFragment()

        onClickAddButton()

        dataObserver(viewModel.data) { list -> setAdapter(list) }
    }

    private fun setAdapter(list: List<FoodProduct>) {
        adapter.submitList(list)
        binding.recyclerView.adapter = adapter
    }

    private fun onClickAddButton() {
        binding.addButton.setOnClickListener {
            viewModel.addMeal()
            findNavController().popBackStack()
        }
    }

    private fun onClickCheckBox(item: FoodProduct) {
        viewModel.addCheckList(item)
    }

}