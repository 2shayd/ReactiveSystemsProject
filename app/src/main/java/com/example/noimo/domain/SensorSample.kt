package com.example.noimo.domain

// Holds sensor data
data class SensorSample (
    val timestampMillis: Long,
    val accelerationMagnitude: Double,
    val audioAmplitude: Double
)