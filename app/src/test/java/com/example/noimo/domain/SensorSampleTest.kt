package com.example.noimo.domain

import junit.framework.TestCase
import org.junit.Test

class SensorSampleTest {

    @Test
    fun sensorSampleStoresValuesCorrectly() {
        val sample = SensorSample(
            timestampMillis = 1000L,
            accelerationMagnitude = 35.5,
            audioAmplitude = 0.82
        )

        TestCase.assertEquals(1000L, sample.timestampMillis)
        TestCase.assertEquals(35.5, sample.accelerationMagnitude)
        TestCase.assertEquals(0.82, sample.audioAmplitude)
    }
}