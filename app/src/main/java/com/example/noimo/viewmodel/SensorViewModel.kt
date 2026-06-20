package com.example.noimo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noimo.data.local.dao.CrashEventDao
import com.example.noimo.data.local.entity.CrashEventEntity
import com.example.noimo.data.remote.auth.CurrentUserProvider
import com.example.noimo.data.remote.auth.SupabaseAuthDataSource
import com.example.noimo.data.remote.datasource.CrashEventRemoteDataSource
import com.example.noimo.data.remote.mapper.toRemoteDto
import com.example.noimo.domain.CrashAnomalyDetector
import com.example.noimo.domain.DetectionResult
import com.example.noimo.domain.SensorSample
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

// Vitoria worked on this.
class SensorViewModel(
    private val crashEventDao: CrashEventDao,
    private val crashEventRemoteDataSource: CrashEventRemoteDataSource,
    private val currentUserProvider: CurrentUserProvider,
    private val authDataSource: SupabaseAuthDataSource
) : ViewModel() {

    private val detector = CrashAnomalyDetector()

    private val _accelerationMagnitude = MutableStateFlow(0.0)
    val accelerationMagnitude: StateFlow<Double> =
        _accelerationMagnitude.asStateFlow()

    private val _audioAmplitude = MutableStateFlow(0.0)
    val audioAmplitude: StateFlow<Double> =
        _audioAmplitude.asStateFlow()

    private val _detectionResult =
        MutableStateFlow<DetectionResult>(DetectionResult.Normal)

    val detectionResult: StateFlow<DetectionResult> =
        _detectionResult.asStateFlow()

    private val _dataSourceLabel =
        MutableStateFlow("No Data")
    val dataSourceLabel: StateFlow<String> = _dataSourceLabel.asStateFlow()

    fun setLiveTracking() {
        _dataSourceLabel.value = "Live Sensor Tracking"
    }

    fun setSimulatedNormal() {
        _dataSourceLabel.value = "Simulated Normal Test"
    }

    fun setSimulatedCrash() {
        _dataSourceLabel.value = "Simulated Crash Test"
    }

    fun updateSensorValues(
        accelerationMagnitude: Double,
        audioAmplitude: Double
    ) {

        _accelerationMagnitude.value = accelerationMagnitude
        _audioAmplitude.value = audioAmplitude

        val sample = SensorSample(
            timestampMillis = System.currentTimeMillis(),
            accelerationMagnitude = accelerationMagnitude,
            audioAmplitude = audioAmplitude
        )

        val result = detector.analyze(sample)

        _detectionResult.value = result

        if (result is DetectionResult.PossibleCrash) {
            onCrashDetected(result.sample)
        }
    }

    fun onCrashDetected(sample: SensorSample) {
        viewModelScope.launch {

            var userId = currentUserProvider.getCurrentUserId()

            if (userId == null) {
                authDataSource.signInTestUser()
                userId = currentUserProvider.getCurrentUserId()
            }

            if (userId == null) {
                Log.e("SensorViewModel", "User ID is still null after sign in")
                return@launch
            }

            Log.d("SensorViewModel", "Current user id: $userId")

            if (userId == null) return@launch

            val id = UUID.randomUUID().toString()
            val storedLocallyAtMillis = System.currentTimeMillis()

            val entity = CrashEventEntity(
                id = id,
                detectedAtMillis = sample.timestampMillis,
                accelerationMagnitude = sample.accelerationMagnitude,
                audioAmplitude = sample.audioAmplitude,
                latitude = null,
                longitude = null,
                storedLocallyAtMillis = storedLocallyAtMillis,
                synced = false
            )

            crashEventDao.insert(entity)

            try {
                val dto = entity.toRemoteDto(userId = userId)

                crashEventRemoteDataSource.uploadCrashEvents(
                    listOf(dto)
                )

                crashEventDao.markSynced(listOf(entity.id))
            } catch (e: Exception) {
                Log.e("SensorViewModel", "Failed to upload remote crash event.")
            }
        }
    }
}