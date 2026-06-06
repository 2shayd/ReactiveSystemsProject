package com.example.noimo.data.local

import junit.framework.TestCase
import org.junit.Test

class CrashEventEntityTest {

    @Test
    fun crashEventEntityStoresValuesCorrectly() {
        val entity = CrashEventEntity(
            id = "test-id",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35f,
            audioAmplitude = 0.9f,
            storedLocallyAtMillis = 1100L,
            synced = false
        )

        TestCase.assertEquals("test-id", entity.id)
        TestCase.assertEquals(1000L, entity.detectedAtMillis)
        TestCase.assertEquals(35f, entity.accelerationMagnitude, 0.001f)
        TestCase.assertEquals(0.9f, entity.audioAmplitude, 0.001f)
        TestCase.assertEquals(1100L, entity.storedLocallyAtMillis)
        TestCase.assertFalse(entity.synced)

    }
}