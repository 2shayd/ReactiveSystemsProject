package com.example.noimo.domain

import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CrashEventTest {
    @Test
    fun crashEventStoresValuesCorrectly() {
        val crashEvent = CrashEvent(
            id = "test-id",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.5,
            audioAmplitude = 0.82,
            latitude = 47.6062,
            longitude = -122.3321,
        )

        TestCase.assertEquals("test-id", crashEvent.id)
        TestCase.assertEquals(1000L, crashEvent.detectedAtMillis)
        TestCase.assertEquals(35.5, crashEvent.accelerationMagnitude, 0.001)
        TestCase.assertEquals(0.82, crashEvent.audioAmplitude, 0.001)
        TestCase.assertEquals(47.6062, crashEvent.latitude!!, 0.001)
        TestCase.assertEquals(-122.3321, crashEvent.longitude!!, 0.001)
    }
}