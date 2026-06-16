package com.example.noimo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// Vitoria worked on this.
// Holds profile screen state using StateFlow.
class ProfileViewModel : ViewModel() {

    private val _name = MutableStateFlow("John Doe")
    val name = _name.asStateFlow()

    private val _phone = MutableStateFlow("(206) 912-1234")
    val phone = _phone.asStateFlow()

    private val _email = MutableStateFlow("example@gmail.com")
    val email = _email.asStateFlow()

    private val _sensorTrackingEnabled = MutableStateFlow(true)
    val sensorTrackingEnabled = _sensorTrackingEnabled.asStateFlow()

    fun setSensorTrackingEnabled(enabled: Boolean) {
        _sensorTrackingEnabled.value = enabled
    }
}