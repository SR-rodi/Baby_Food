package com.example.artyomkafood.feature_food.presentation.correct

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.databinding.FragmentCorrectBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CorrectFragment : BaseFragment<FragmentCorrectBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentCorrectBinding.inflate(inflater)

    private val args by navArgs<CorrectFragmentArgs>()

    private val viewModel by viewModel<CorrectViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text = args.items.product_name
        binding.counter.text.insert(0,args.items.meal_volume.toString())

        binding.editButton.setOnClickListener {
            viewModel.updateMeal(args.items,binding.counter.text.toString())
        }
    }
}