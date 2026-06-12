package com.example.noimo.data.remote.mapper

import com.example.noimo.data.local.entity.CrashEventEntity
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CrashEventRemoteMapperTest {

    @Test
    fun crashEventEntityMapsToRemoteDto() {
        val entity = CrashEventEntity(
            id = "550e8400-e29b-41d4-a716-446655440000",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.5,
            audioAmplitude = 0.9,
            latitude = 47.6062,
            longitude = -122.3321,
            storedLocallyAtMillis = 1100L,
            synced = false
        )

        val dto = entity.toRemoteDto(
            userId = "123e4567-e89b-12d3-a456-426614174000"
        )

        assertEquals("550e8400-e29b-41d4-a716-446655440000", dto.id)
        assertEquals("123e4567-e89b-12d3-a456-426614174000", dto.userId)
        assertEquals(1000L, dto.detectedAtMillis)
        assertEquals(35.5, dto.accelerationMagnitude, 0.001)
        assertEquals(0.9, dto.audioAmplitude, 0.001)
        assertEquals(47.6062, dto.latitude!!, 0.001)
        assertEquals(-122.3321, dto.longitude!!, 0.001)
        assertEquals(1100L, dto.storedLocallyAtMillis)
    }

    @Test
    fun crashEventEntityWithNullLocationMapsToRemoteDto() {
        val entity = CrashEventEntity(
            id = "550e8400-e29b-41d4-a716-446655440000",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.5,
            audioAmplitude = 0.9,
            latitude = null,
            longitude = null,
            storedLocallyAtMillis = 1100L,
            synced = false
        )

        val dto = entity.toRemoteDto(
            userId = "123e4567-e89b-12d3-a456-426614174000"
        )

        assertEquals(null, dto.latitude)
        assertEquals(null, dto.longitude)
    }
}