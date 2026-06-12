package com.example.noimo.data.local

import junit.framework.TestCase
import org.junit.Test

class CrashEventEntityTest {

    @Test
    fun crashEventEntityStoresValuesCorrectly() {
        val entity = CrashEventEntity(
            id = "test-id",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.0,
            audioAmplitude = 0.9,
            latitude = 47.6062,
            longitude = -122.3321,
            storedLocallyAtMillis = 1100L,
            synced = false
        )

        TestCase.assertEquals("test-id", entity.id)
        TestCase.assertEquals(1000L, entity.detectedAtMillis)
        TestCase.assertEquals(35.0, entity.accelerationMagnitude, 0.001)
        TestCase.assertEquals(0.9, entity.audioAmplitude, 0.001)
        TestCase.assertEquals(1100L, entity.storedLocallyAtMillis)
        TestCase.assertEquals(47.6062, entity.latitude!!, 0.001)
        TestCase.assertEquals(-122.3321, entity.longitude!!, 0.001)
        TestCase.assertFalse(entity.synced)

    }
}