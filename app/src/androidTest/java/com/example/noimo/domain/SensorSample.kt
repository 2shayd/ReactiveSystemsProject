// Jesse worked on this
package com.example.noimo.domain

// Holds sensor data
data class SensorSample (
    val timestampMillis: Long,
    val accelerationMagnitude: Float,
    val audioAmplitude: Float
)