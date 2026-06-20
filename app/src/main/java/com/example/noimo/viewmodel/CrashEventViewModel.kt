package com.example.noimo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.noimo.domain.CrashEvent
import com.example.noimo.domain.CrashEventRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn

//shayla worked on this

/**
 * Temporary ViewModel used to support RecordsScreen UI development.
 *
 * Currently exposes sample CrashEvent data through StateFlow so the
 * Records screen can be implemented and tested before repository and
 * Room integration is completed.
 *
 * Future implementation should replace the sample data with events
 * retrieved from RoomCrashEventRepository while preserving the
 * public StateFlow interface used by the UI.
 */

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