package com.example.noimo.data.remote.dto

import junit.framework.TestCase.assertTrue
import kotlinx.serialization.json.Json
import org.junit.Test

class CrashEventRemoteDtoTest {

    private val json = Json {
        encodeDefaults = true
    }

    @Test
    fun crashEventRemoteDtoSerializesWithSupabaseColumnNames() {
        val dto = CrashEventRemoteDto(
            id = "550e8400-e29b-41d4-a716-446655440000",
            userId = "123e4567-e89b-12d3-a456-426614174000",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.5,
            audioAmplitude = 0.9,
            latitude = 47.6062,
            longitude = -122.3321,
            storedLocallyAtMillis = 1100L
        )

        val serialized = json.encodeToString(
            CrashEventRemoteDto.serializer(),
            dto
        )

        assertTrue(serialized.contains("\"user_id\""))
        assertTrue(serialized.contains("\"detected_at_millis\""))
        assertTrue(serialized.contains("\"acceleration_magnitude\""))
        assertTrue(serialized.contains("\"audio_amplitude\""))
        assertTrue(serialized.contains("\"stored_locally_at_millis\""))
    }
}