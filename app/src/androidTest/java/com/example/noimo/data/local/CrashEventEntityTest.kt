package com.example.noimo.data.local

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
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

        assertEquals("test-id", entity.id)
        assertEquals(1000L, entity.detectedAtMillis)
        assertEquals(35f, entity.accelerationMagnitude, 0.001f)
        assertEquals(0.9f, entity.audioAmplitude, 0.001f)
        assertEquals(1100L, entity.storedLocallyAtMillis)
        assertFalse(entity.synced)

    }
}