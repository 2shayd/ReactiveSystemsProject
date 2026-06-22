package com.example.noimo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import com.example.noimo.domain.CrashEvent
import com.example.noimo.domain.CrashEventRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

//shayla worked on this


class CrashEventViewModel(
    private val repository: CrashEventRepository
) : ViewModel() {

    val events: StateFlow<List<CrashEvent>> =
        repository.observeCrashEvents()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )
}