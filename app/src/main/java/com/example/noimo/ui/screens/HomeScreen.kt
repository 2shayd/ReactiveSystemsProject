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

// Shayla worked on the original HomeScreen.
// Vitoria updated this screen to connect SensorViewModel and display sensor values.
@Composable
fun HomeScreen(
    sensorViewModel: SensorViewModel = viewModel()
) {
    val acceleration by sensorViewModel.accelerationMagnitude.collectAsState()
    val audio by sensorViewModel.audioAmplitude.collectAsState()
    val detectionResult by sensorViewModel.detectionResult.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome to NoiMo!",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Acceleration: ${"%.2f".format(acceleration)}")
        Text(text = "Audio Amplitude: ${"%.2f".format(audio)}")
        Text(text = "Detection Result: ${formatDetectionResult(detectionResult)}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                sensorViewModel.updateSensorValues(
                    accelerationMagnitude = 3.0f,
                    audioAmplitude = 20.0f
                )
            }
        ) {
            Text("Simulate Normal Values")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
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