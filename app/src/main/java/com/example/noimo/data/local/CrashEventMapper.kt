package com.example.noimo.data.local

import com.example.noimo.domain.CrashEvent

fun CrashEventEntity.toDomain(): CrashEvent {
    return CrashEvent(
        id = id,
        detectedAtMillis = detectedAtMillis,
        accelerationMagnitude = accelerationMagnitude,
        audioAmplitude = audioAmplitude
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
        storedLocallyAtMillis = storedLocallyAtMillis,
        synced = false
    )
}