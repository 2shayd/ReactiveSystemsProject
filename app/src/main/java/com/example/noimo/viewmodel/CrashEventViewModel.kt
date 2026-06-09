package com.example.noimo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.noimo.domain.CrashEvent

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
class CrashEventViewModel : ViewModel() {

    // TODO: Replace sample data with repository-backed StateFlow when
    //  CrashEvent repository/ViewModel integration is implemented.
    private val _events = MutableStateFlow(
        listOf(
            CrashEvent(
                id = "1",
                detectedAtMillis = System.currentTimeMillis(),
                accelerationMagnitude = 12.5f,
                audioAmplitude = 0.82f
            ),
            CrashEvent(
                id = "2",
                detectedAtMillis = System.currentTimeMillis() - 60000,
                accelerationMagnitude = 9.8f,
                audioAmplitude = 0.67f
            )
        )
    )

    val events: StateFlow<List<CrashEvent>> =
        _events.asStateFlow()
}