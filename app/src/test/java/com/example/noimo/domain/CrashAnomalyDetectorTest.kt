package com.example.noimo.domain

import junit.framework.TestCase
import org.junit.Test

class CrashAnomalyDetectorTest {

    private val detector = CrashAnomalyDetector()

    @Test
    fun abnormalSampleReturnsCrash() {
        val sample = SensorSample(
            timestampMillis = 1000L,
            accelerationMagnitude = 35f,
            audioAmplitude = 0.9f
        )

        val result = detector.analyze(sample)

        TestCase.assertTrue(result is DetectionResult.PossibleCrash)
    }

    @Test
    fun normalSampleReturnsNormal() {
        val sample = SensorSample(
            timestampMillis = 1000L,
            accelerationMagnitude = 9.8f,
            audioAmplitude = 0.2f
        )

        val result = detector.analyze(sample)

        TestCase.assertTrue(result is DetectionResult.Normal)
    }
}