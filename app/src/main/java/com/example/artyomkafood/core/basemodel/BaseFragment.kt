package com.example.artyomkafood.core.basemodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    abstract fun initBinding(inflater: LayoutInflater): B?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = initBinding(inflater)
        return binding.root
    }

    protected fun <T : Any> dataObserver(flow: Flow<List<T>>, block: (list: List<T>) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            flow.collect { list ->
                block(list)
            }
        }
    }

    protected fun <I : Any> visibilityStateObserve(itemFlow: Flow<I>, block: (I) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            itemFlow.collect { boolean ->
                block(boolean)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}