package com.example.noimo.domain

import junit.framework.TestCase
import org.junit.Test

class DetectionResultTest {

    @Test
    fun normalIsDetectionResult() {
        val result: DetectionResult = DetectionResult.Normal

        TestCase.assertTrue(result is DetectionResult.Normal)
    }

    @Test
    fun possibleCrashStoresSensorSample() {
        val sample = SensorSample(
            timestampMillis = 2000L,
            accelerationMagnitude = 45f,
            audioAmplitude = 0.95f
        )

        val result = DetectionResult.PossibleCrash(sample)

        TestCase.assertEquals(sample, result.sample)
        TestCase.assertEquals(2000L, result.sample.timestampMillis)
        TestCase.assertEquals(45f, result.sample.accelerationMagnitude, 0.001f)
        TestCase.assertEquals(0.95f, result.sample.audioAmplitude, 0.001f)
    }

    @Test
    fun possibleCrashIsDetectionResult() {
        val sample = SensorSample(
            timestampMillis = 2000L,
            accelerationMagnitude = 45f,
            audioAmplitude = 0.95f
        )

        val result: DetectionResult = DetectionResult.PossibleCrash(sample)

        TestCase.assertTrue(result is DetectionResult.PossibleCrash)
    }
}