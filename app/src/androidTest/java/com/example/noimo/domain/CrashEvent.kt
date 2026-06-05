// Jesse worked on this
package com.example.noimo.domain

// Holds relevant info for a detected crash event.
data class CrashEvent (
    val id: String,
    val detectedAtMillis: Long,
    val accelerationMagnitude: Float,
    val audioAmplitude: Float
)