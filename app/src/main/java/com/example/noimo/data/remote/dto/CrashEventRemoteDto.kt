package com.example.noimo.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrashEventRemoteDto(
    val id: String,

    @SerialName("user_id")
    val userId: String,

    @SerialName("detected_at_millis")
    val detectedAtMillis: Long,

    @SerialName("acceleration_magnitude")
    val accelerationMagnitude: Double,

    @SerialName("audio_amplitude")
    val audioAmplitude: Double,

    val latitude: Double?,

    val longitude: Double?,

    @SerialName("stored_locally_at_millis")
    val storedLocallyAtMillis: Long
)