package com.example.noimo.data.local.mapper

import com.example.noimo.data.local.entity.CrashEventEntity
import com.example.noimo.domain.CrashEvent
import junit.framework.TestCase
import org.junit.Test

class CrashEventMapperTest {

    @Test
    fun crashEventEntityMapsToDomain() {
        val entity = CrashEventEntity(
            id = "event-1",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.0,
            audioAmplitude = 0.9,
            latitude = 47.6062,
            longitude = -122.3321,
            storedLocallyAtMillis = 1100L,
            synced = false
        )

        val domain = entity.toDomain()

        TestCase.assertEquals("event-1", domain.id)
        TestCase.assertEquals(1000L, domain.detectedAtMillis)
        TestCase.assertEquals(35.0, domain.accelerationMagnitude, 0.001)
        TestCase.assertEquals(0.9, domain.audioAmplitude, 0.001)
        TestCase.assertEquals(47.6062, domain.latitude!!, 0.001)
        TestCase.assertEquals(-122.3321, domain.longitude!!, 0.001)
    }

    @Test
    fun crashEventMapsToCrashEventEntity() {
        val event = CrashEvent(
            id = "event-1",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.0,
            audioAmplitude = 0.9,
            latitude = 47.6062,
            longitude = -122.3321,
        )

        val entity = event.toEntity(storedLocallyAtMillis = 1100L)

        TestCase.assertEquals("event-1", entity.id)
        TestCase.assertEquals(1000L, entity.detectedAtMillis)
        TestCase.assertEquals(35.0, entity.accelerationMagnitude, 0.001)
        TestCase.assertEquals(0.9, entity.audioAmplitude, 0.001)
        TestCase.assertEquals(1100L, entity.storedLocallyAtMillis)
        TestCase.assertEquals(47.6062, entity.latitude!!, 0.001)
        TestCase.assertEquals(-122.3321, entity.longitude!!, 0.001)
        TestCase.assertFalse(entity.synced)
    }
}