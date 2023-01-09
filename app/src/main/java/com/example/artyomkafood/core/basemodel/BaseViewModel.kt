package com.example.artyomkafood.core.basemodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel<T : Any> : ViewModel() {
    protected val _data = MutableSharedFlow<List<T>>()
    val data = _data.asSharedFlow()

}