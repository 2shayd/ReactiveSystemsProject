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
            accelerationMagnitude = 45.0,
            audioAmplitude = 0.95
        )

        val result = DetectionResult.PossibleCrash(sample)

        TestCase.assertEquals(sample, result.sample)
        TestCase.assertEquals(2000L, result.sample.timestampMillis)
        TestCase.assertEquals(45.0, result.sample.accelerationMagnitude)
        TestCase.assertEquals(0.95, result.sample.audioAmplitude)
    }

    @Test
    fun possibleCrashIsDetectionResult() {
        val sample = SensorSample(
            timestampMillis = 2000L,
            accelerationMagnitude = 45.0,
            audioAmplitude = 0.95
        )

        val result: DetectionResult = DetectionResult.PossibleCrash(sample)

        TestCase.assertTrue(result is DetectionResult.PossibleCrash)
    }
}