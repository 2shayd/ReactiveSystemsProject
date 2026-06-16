package com.example.noimo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.noimo.domain.CrashAnomalyDetector
import com.example.noimo.domain.DetectionResult
import com.example.noimo.domain.SensorSample
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Vitoria worked on this.
class SensorViewModel : ViewModel() {

    private val detector = CrashAnomalyDetector()

    private val _accelerationMagnitude = MutableStateFlow(0f)
    val accelerationMagnitude: StateFlow<Float> =
        _accelerationMagnitude.asStateFlow()

    private val _audioAmplitude = MutableStateFlow(0f)
    val audioAmplitude: StateFlow<Float> =
        _audioAmplitude.asStateFlow()

    private val _detectionResult =
        MutableStateFlow<DetectionResult>(DetectionResult.Normal)

    val detectionResult: StateFlow<DetectionResult> =
        _detectionResult.asStateFlow()

    fun updateSensorValues(
        accelerationMagnitude: Float,
        audioAmplitude: Float
    ) {

        _accelerationMagnitude.value = accelerationMagnitude
        _audioAmplitude.value = audioAmplitude

        val sample = SensorSample(
            timestampMillis = System.currentTimeMillis(),
            accelerationMagnitude = accelerationMagnitude,
            audioAmplitude = audioAmplitude
        )

        _detectionResult.value = detector.analyze(sample)
    }
}