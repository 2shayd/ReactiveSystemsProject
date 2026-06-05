// Jesse worked on this
package com.example.noimo.domain

sealed class DetectionResult {
    data object Normal : DetectionResult()

    data class PossibleCrash(
        val sample: SensorSample
    ) : DetectionResult()
}