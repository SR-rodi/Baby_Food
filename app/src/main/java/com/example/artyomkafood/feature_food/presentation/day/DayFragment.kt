package com.example.artyomkafood.feature_food.presentation.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.databinding.FragmentDayBinding
import com.example.artyomkafood.feature_food.presentation.day.adapter.DayAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DayFragment : BaseFragment<FragmentDayBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentDayBinding.inflate(inflater)

    private val viewModel by viewModel<DayViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSchedule()

        binding.date.text = viewModel.getDate()

        binding.nextDayButton.setOnClickListener {
            viewModel.onClickDateButton(true)
            binding.date.text = viewModel.getDate()
        }
        binding.backDayButton.setOnClickListener {
            viewModel.onClickDateButton(false)
            binding.date.text = viewModel.getDate()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collect { list->
                binding.recyclerView.adapter =
                    DayAdapter(list,{scheduleId-> navigation(scheduleId) }){ meal->
                    viewModel.onSwipeEvent(meal)
                }
            }
        }
    }


    private fun navigation(scheduleId:Int) {
        findNavController()
            .navigate(DayFragmentDirections.actionDayFragmentToAddFragment(viewModel.setDate(),scheduleId))
    }

}