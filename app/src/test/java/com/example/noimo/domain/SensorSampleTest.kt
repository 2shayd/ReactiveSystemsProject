package com.example.noimo.domain

import junit.framework.TestCase
import org.junit.Test

class SensorSampleTest {

    @Test
    fun sensorSampleStoresValuesCorrectly() {
        val sample = SensorSample(
            timestampMillis = 1000L,
            accelerationMagnitude = 35.5f,
            audioAmplitude = 0.82f
        )

        TestCase.assertEquals(1000L, sample.timestampMillis)
        TestCase.assertEquals(35.5f, sample.accelerationMagnitude, 0.001f)
        TestCase.assertEquals(0.82f, sample.audioAmplitude, 0.001f)
    }
}