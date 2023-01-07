package com.example.artyomkafood.feature_day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.artyomkafood.core.basemodel.BaseFragment
import com.example.artyomkafood.databinding.FragmentDayBinding

class DayFragment : BaseFragment<FragmentDayBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentDayBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.itemBreakfast.addOnClickListenerInButton()
    }

}