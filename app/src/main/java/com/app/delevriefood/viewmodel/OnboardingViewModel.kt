package com.app.delevriefood.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel : ViewModel() {
    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    fun onPageChanged(position: Int) {
        _currentPage.value = position
    }

    fun onNextPage() {
        _currentPage.value = (_currentPage.value + 1).coerceAtMost(2)
    }
}
