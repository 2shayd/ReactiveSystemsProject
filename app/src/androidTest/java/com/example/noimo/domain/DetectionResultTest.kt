package com.example.noimo.domain

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class DetectionResultTest {

    @Test
    fun normalIsDetectionResult() {
        val result: DetectionResult = DetectionResult.Normal

        assertTrue(result is DetectionResult.Normal)
    }

    @Test
    fun possibleCrashStoresSensorSample() {
        val sample = SensorSample(
            timestampMillis = 2000L,
            accelerationMagnitude = 45f,
            audioAmplitude = 0.95f
        )

        val result = DetectionResult.PossibleCrash(sample)

        assertEquals(sample, result.sample)
        assertEquals(2000L, result.sample.timestampMillis)
        assertEquals(45f, result.sample.accelerationMagnitude, 0.001f)
        assertEquals(0.95f, result.sample.audioAmplitude, 0.001f)
    }

    @Test
    fun possibleCrashIsDetectionResult() {
        val sample = SensorSample(
            timestampMillis = 2000L,
            accelerationMagnitude = 45f,
            audioAmplitude = 0.95f
        )

        val result: DetectionResult = DetectionResult.PossibleCrash(sample)

        assertTrue(result is DetectionResult.PossibleCrash)
    }
}