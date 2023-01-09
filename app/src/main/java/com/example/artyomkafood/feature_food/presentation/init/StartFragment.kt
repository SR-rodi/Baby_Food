package com.example.artyomkafood.feature_food.presentation.init

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.core.extensions.checkFirstStart
import com.example.artyomkafood.databinding.FragmentStartBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : BaseFragment<FragmentStartBinding>() {

    private val viewModel by viewModel<StartViewModel>()

    override fun initBinding(inflater: LayoutInflater) = FragmentStartBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onCheckFirstStart(checkFirstStart())

        stateObserver()

    }

    private fun stateObserver() =
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collect { isNext ->
                if (isNext)
                    findNavController().navigate(StartFragmentDirections.actionStartFragmentToDayFragment())
            }
        }

    private fun onCheckFirstStart(isFirstStart: Boolean) {
        if (isFirstStart) viewModel.setInfoInDataBase()
        else viewModel.startNewFragment()
    }
}