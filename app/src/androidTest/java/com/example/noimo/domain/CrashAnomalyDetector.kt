package com.example.noimo.domain

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