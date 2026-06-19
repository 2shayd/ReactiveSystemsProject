package com.example.noimo.domain

import junit.framework.TestCase
import org.junit.Test

class CrashAnomalyDetectorTest {

    private val detector = CrashAnomalyDetector()

    @Test
    fun abnormalSampleReturnsCrash() {
        val sample = SensorSample(
            timestampMillis = 1000L,
            accelerationMagnitude = 35.0,
            audioAmplitude = 0.9
        )

        val result = detector.analyze(sample)

        TestCase.assertTrue(result is DetectionResult.PossibleCrash)
    }

    @Test
    fun normalSampleReturnsNormal() {
        val sample = SensorSample(
            timestampMillis = 1000L,
            accelerationMagnitude = 9.8,
            audioAmplitude = 0.2
        )

        val result = detector.analyze(sample)

        TestCase.assertTrue(result is DetectionResult.Normal)
    }
}