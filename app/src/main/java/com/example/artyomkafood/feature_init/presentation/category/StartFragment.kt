package com.example.artyomkafood.feature_init.presentation.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.databinding.FragmentStartBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : BaseFragment<FragmentStartBinding>() {

    private val viewModel by viewModel<StartViewModel>()

    override fun initBinding(inflater: LayoutInflater) = FragmentStartBinding.inflate(inflater)

    private val adapter by lazy {
        CategoryAdapter{id->navigation(id)}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setInfoInDataBase()
        binding.recyclerView.adapter = adapter
        dataObserve()
    }

    private fun dataObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collect { listCategory ->
               adapter.submitList(listCategory)
            }
        }
    }

    private fun navigation(id: Int?) {
        if (id != null)
            findNavController()
                .navigate(StartFragmentDirections.actionStartFragmentToFoodFragment(id))
    }
}