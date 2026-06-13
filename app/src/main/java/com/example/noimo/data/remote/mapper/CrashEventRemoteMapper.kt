package com.example.noimo.data.remote.mapper

import com.example.noimo.data.local.entity.CrashEventEntity
import com.example.noimo.data.remote.dto.CrashEventRemoteDto

fun CrashEventEntity.toRemoteDto(userId: String): CrashEventRemoteDto {
    return CrashEventRemoteDto(
        id = id,
        userId = userId,
        detectedAtMillis = detectedAtMillis,
        accelerationMagnitude = accelerationMagnitude,
        audioAmplitude = audioAmplitude,
        latitude = latitude,
        longitude = longitude,
        storedLocallyAtMillis = storedLocallyAtMillis
    )
}