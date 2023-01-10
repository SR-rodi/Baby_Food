package com.example.artyomkafood.feature_food.presentation.day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.artyomkafood.core.ClickState
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.extensions.createEditDialog
import com.example.artyomkafood.databinding.FragmentDayBinding
import com.example.artyomkafood.feature_food.presentation.day.adapter.DayAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class DayFragment : BaseFragment<FragmentDayBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentDayBinding.inflate(inflater)

    private val viewModel by viewModel<DayViewModel>()

    private val adapter by lazy {
        DayAdapter { onClickListener(it) }
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

    private fun navigation(scheduleId: Int?) {
        if (scheduleId != null)
            findNavController()
                .navigate(DayFragmentDirections.actionDayFragmentToAddFragment(viewModel.setDate(),
                    scheduleId))
    }

    private fun onClickListener(state: ClickState) {
        when (state) {
            ClickState.ADD_BUTTON -> navigation(state.index)
            ClickState.SWIPE -> viewModel.onSwipeEvent(state.meal)
            ClickState.CLICK_ITEM ->
                createEditDialog(state.meal?.product_name, state.meal?.meal_volume) { newVolume ->
                    viewModel.updateMeal(state.meal, newVolume)
                }
        }
    }
}