package com.example.noimo.domain

// Determines if sensor data is a possible crash. If it exceeds values then the detection result
// becomes a possible crash with sensor values.
class CrashAnomalyDetector {
    fun analyze(sample: SensorSample): DetectionResult {
        val hardImpact = sample.accelerationMagnitude > 30f
        val loudSound = sample.audioAmplitude > 0.8f

        return if (hardImpact && loudSound) {
            DetectionResult.PossibleCrash(sample)
        } else {
            DetectionResult.Normal
        }
    }
}