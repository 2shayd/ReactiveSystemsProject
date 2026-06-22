package com.example.noimo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noimo.domain.CrashEvent
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// shayla worked on this
@Composable
fun IncidentDetailScreen(
    event: CrashEvent?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Incident Details",
            style = MaterialTheme.typography.headlineMedium
        )

        if (event == null) {
            Text(
                text = "Incident not found.",
                modifier = Modifier.padding(top = 16.dp)
            )
        } else {
            Card(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Timestamp",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = formatTimestamp(event.detectedAtMillis)
                    )

                    Text(
                        text = "Microphone Value",
                        modifier = Modifier.padding(top = 16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "Audio Amplitude: ${event.audioAmplitude}"
                    )

                    Text(
                        text = "Accelerometer Value",
                        modifier = Modifier.padding(top = 16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "Acceleration Magnitude: ${event.accelerationMagnitude}"
                    )

                    Text(
                        text = "Location",
                        modifier = Modifier.padding(top = 16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "Location unavailable"
                    )

                    Text(
                        text = "Event ID: ${event.id}",
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

private fun formatTimestamp(millis: Long): String {
    val formatter = SimpleDateFormat(
        "MMM dd, yyyy h:mm a",
        Locale.getDefault()
    )

    return formatter.format(Date(millis))
}