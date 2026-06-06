package com.example.noimo.data.local

import com.example.noimo.domain.CrashEvent
import junit.framework.TestCase
import org.junit.Test

class CrashEventMapperTest {

    @Test
    fun crashEventEntityMapsToDomain() {
        val entity = CrashEventEntity(
            id = "event-1",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35f,
            audioAmplitude = 0.9f,
            storedLocallyAtMillis = 1100L,
            synced = false
        )

        val domain = entity.toDomain()

        TestCase.assertEquals("event-1", domain.id)
        TestCase.assertEquals(1000L, domain.detectedAtMillis)
        TestCase.assertEquals(35f, domain.accelerationMagnitude, 0.001f)
        TestCase.assertEquals(0.9f, domain.audioAmplitude, 0.001f)
    }

    @Test
    fun crashEventMapsToCrashEventEntity() {
        val event = CrashEvent(
            id = "event-1",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35f,
            audioAmplitude = 0.9f
        )

        val entity = event.toEntity(storedLocallyAtMillis = 1100L)

        TestCase.assertEquals("event-1", entity.id)
        TestCase.assertEquals(1000L, entity.detectedAtMillis)
        TestCase.assertEquals(35f, entity.accelerationMagnitude, 0.001f)
        TestCase.assertEquals(0.9f, entity.audioAmplitude, 0.001f)
        TestCase.assertEquals(1100L, entity.storedLocallyAtMillis)
        TestCase.assertFalse(entity.synced)
    }
}