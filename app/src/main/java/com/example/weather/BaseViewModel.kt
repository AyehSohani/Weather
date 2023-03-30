package com.example.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(coroutineContext: CoroutineContext? = null) : ViewModel(),
    CoroutineScope {
    override val coroutineContext: CoroutineContext =
        coroutineContext ?: viewModelScope.coroutineContext
}