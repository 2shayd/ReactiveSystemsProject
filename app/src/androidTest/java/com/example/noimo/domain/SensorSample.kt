// Jesse worked on this
package com.example.noimo.domain

data class SensorSample (
    val timestampMillis: Long,
    val accelerationMagnitude: Float,
    val audioAmplitude: Float
)