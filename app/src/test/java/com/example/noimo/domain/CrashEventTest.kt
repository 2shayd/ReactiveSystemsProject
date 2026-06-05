package com.example.noimo.domain

import junit.framework.TestCase
import org.junit.Test

class CrashEventTest {
    @Test
    fun crashEventStoresValuesCorrectly() {
        val crashEvent = CrashEvent(
            id = "test-id",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.5f,
            audioAmplitude = 0.82f
        )

        TestCase.assertEquals("test-id", crashEvent.id)
        TestCase.assertEquals(1000L, crashEvent.detectedAtMillis)
        TestCase.assertEquals(35.5f, crashEvent.accelerationMagnitude, 0.001f)
        TestCase.assertEquals(0.82f, crashEvent.audioAmplitude, 0.001f)
    }
}