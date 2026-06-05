// Jesse worked on this
package com.example.noimo.domain

// Object that holds sensor data of potential crash. Or, if detector finds data to be normal, a
// 'normal' object.
sealed class DetectionResult {
    data object Normal : DetectionResult()

    data class PossibleCrash(
        val sample: SensorSample
    ) : DetectionResult()
}