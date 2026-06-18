package com.example.noimo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noimo.domain.DetectionResult
import com.example.noimo.viewmodel.SensorViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.example.noimo.data.sensors.SensorTrackingManager
import androidx.compose.foundation.layout.fillMaxWidth

// Shayla worked on the original HomeScreen.
// Vitoria updated this screen to connect SensorViewModel and display sensor values.
@Composable
fun HomeScreen(
    sensorViewModel: SensorViewModel = viewModel()
) {
    val acceleration by sensorViewModel.accelerationMagnitude.collectAsState()
    val audio by sensorViewModel.audioAmplitude.collectAsState()
    val detectionResult by sensorViewModel.detectionResult.collectAsState()
    val dataSourceLabel by sensorViewModel.dataSourceLabel.collectAsState()
    val context = LocalContext.current

    val sensorTrackingManager = SensorTrackingManager(context) { acceleration ->
        sensorViewModel.updateSensorValues(
            accelerationMagnitude = acceleration,
            audioAmplitude = audio
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome to NoiMo!",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Acceleration: ${"%.2f".format(acceleration)}",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Audio Amplitude: ${"%.2f".format(audio)}",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Detection Result: ${formatDetectionResult(detectionResult)}",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Mode: $dataSourceLabel",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                sensorViewModel.setLiveTracking()
                sensorTrackingManager.startTracking()
            }
        ) {
            Text("Start Sensor Tracking")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                sensorViewModel.setSimulatedNormal()

                sensorViewModel.updateSensorValues(
                    accelerationMagnitude = 3.0f,
                    audioAmplitude = 20.0f
                )
            }
        ) {
            Text("Simulate Normal Values")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                sensorViewModel.setSimulatedCrash()

                sensorViewModel.updateSensorValues(
                    accelerationMagnitude = 50.0f,
                    audioAmplitude = 100.0f
                )
            }
        ) {
            Text("Simulate Possible Crash")
        }
    }
}

private fun formatDetectionResult(result: DetectionResult): String {
    return when (result) {
        DetectionResult.Normal -> "Normal"
        is DetectionResult.PossibleCrash -> "Possible Crash"
    }
}