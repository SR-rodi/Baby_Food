@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.artyomkafood.feature_food.presentation.day.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.artyomkafood.R
import com.example.artyomkafood.core.settings.ClickState
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.core.database.Schedule
import com.example.artyomkafood.core.extensions.createCarousel
import com.example.artyomkafood.core.extensions.createEditDialog
import com.example.artyomkafood.core.extensions.setOnPositionListener
import com.example.artyomkafood.databinding.FragmentDayBinding
import com.example.artyomkafood.feature_food.presentation.day.adapter.DateAdapter
import com.example.artyomkafood.feature_food.presentation.day.adapter.DayAdapter
import com.example.artyomkafood.feature_food.presentation.day.viewmodel.DayViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class DayFragment : BaseFragment<FragmentDayBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentDayBinding.inflate(inflater)

    private val viewModel by viewModel<DayViewModel>()

    private val adapter by lazy { DayAdapter { onClickListener(it) } }

    private val dateAdapter by lazy {
        DateAdapter { onClickDate() }
    }

    private fun onClickDate() {
        binding.toolbar.frameCalendar.isVisible = true
        binding.toolbar.calendar.date = viewModel.getDateLong()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startSettings()

        binding.toolbar.calendar.setOnDateChangeListener { _, year, month, day ->
            viewModel.setDate(day, month, year)
            binding.toolbar.frameCalendar.isVisible = false
            binding.toolbar.viewPagerDate.setCurrentItem(day - 1, false)
        }

        dataObserver(viewModel.schedules) { schedules ->
            setAdapter(schedules)
        }
        dataObserver(viewModel.dateList) { dateList ->
            dateAdapter.submitList(dateList)
            startSettings()
        }
    }

    private fun setVisibilityButton(position: Int) {
        binding.toolbar.backButton.isVisible = position == 0
        binding.toolbar.nextButton.isVisible = position == viewModel.getDayOfMonth() - 1
    }

    private fun startSettings() {

        binding.toolbar.apply {
            viewPagerDate.apply {
                adapter = dateAdapter
                setCurrentItem(viewModel.getDay() - 1, true)
                createCarousel()
                setOnPositionListener { position ->
                    setVisibilityButton(position)
                    viewModel.setDay(position)
                }
            }
            month.text = resources.getStringArray(R.array.month_array)[viewModel.getMonth()]
            collapsing.title = viewModel.getDateString()
        }
    }

    private fun setAdapter(list: List<Schedule>) {
        adapter.submitList(list)
        binding.recyclerView.adapter = adapter

    }

    private fun navigation(scheduleId: Int?) {
        if (scheduleId != null) findNavController()
            .navigate(DayFragmentDirections.actionDayFragmentToAddFragment(
                    viewModel.getDateLong(),
                    scheduleId
                )
            )
    }

    private fun onClickListener(state: ClickState) {
        when (state) {
            ClickState.ADD_BUTTON -> navigation(state.index)
            ClickState.SWIPE -> viewModel.onSwipeEvent(state.meal)
            ClickState.CLICK_ITEM -> createEditDialog(
                state.meal?.product_name,
                state.meal?.meal_volume
            ) { newVolume ->
                viewModel.updateMeal(state.meal, newVolume)
            }
        }
    }
}