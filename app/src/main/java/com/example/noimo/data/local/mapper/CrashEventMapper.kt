package com.example.noimo.data.local.mapper

import com.example.noimo.data.local.entity.CrashEventEntity
import com.example.noimo.domain.CrashEvent

fun CrashEventEntity.toDomain(): CrashEvent {
    return CrashEvent(
        id = id,
        detectedAtMillis = detectedAtMillis,
        accelerationMagnitude = accelerationMagnitude,
        audioAmplitude = audioAmplitude,
        longitude = longitude,
        latitude = latitude,
    )
}

fun CrashEvent.toEntity(
    storedLocallyAtMillis: Long = System.currentTimeMillis()
): CrashEventEntity {
    return CrashEventEntity(
        id = id,
        detectedAtMillis = detectedAtMillis,
        accelerationMagnitude = accelerationMagnitude,
        audioAmplitude = audioAmplitude,
        longitude = longitude,
        latitude = latitude,
        storedLocallyAtMillis = storedLocallyAtMillis,
        synced = false
    )
}