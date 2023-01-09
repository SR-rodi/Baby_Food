package com.example.artyomkafood.feature_food.presentation.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.databinding.FragmentDayBinding
import com.example.artyomkafood.feature_food.presentation.day.adapter.DayAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DayFragment : BaseFragment<FragmentDayBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentDayBinding.inflate(inflater)

    private val viewModel by viewModel<DayViewModel>()

    private val adapter by lazy {
        DayAdapter(
            onClickAddButton = { scheduleId -> navigation(scheduleId) },
            onSwipeEvent = { meal -> viewModel.onSwipeEvent(meal) },
            onClickFoodItem = {
                findNavController().navigate(DayFragmentDirections.actionDayFragmentToCorrectFragment(it))}
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startSettings()

        onClickSetDate(binding.nextDayButton, true)
        onClickSetDate(binding.backDayButton, false)

        dataObserver(viewModel.data) { list -> setAdapter(list) }

    }

    private fun startSettings() {
        viewModel.getSchedule()
        binding.date.text = viewModel.getDate()

    }

    private fun setAdapter(list: List<Schedule>) {
        adapter.submitList(list)
        binding.recyclerView.adapter = adapter
    }

    private fun onClickSetDate(button: FloatingActionButton, isNext: Boolean) =
        button.setOnClickListener {
            viewModel.onClickDateButton(isNext)
            binding.date.text = viewModel.getDate()
        }

    private fun navigation(scheduleId: Int) =
        findNavController()
            .navigate(DayFragmentDirections.actionDayFragmentToAddFragment(viewModel.setDate(),
                scheduleId))
}