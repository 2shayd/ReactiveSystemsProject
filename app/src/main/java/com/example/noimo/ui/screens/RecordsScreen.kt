package com.example.noimo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

//shayla worked on this
@Composable
fun RecordsScreen(
    events: List<CrashEvent>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Records",
            style = MaterialTheme.typography.headlineMedium
        )

        LazyColumn {
            items(events) { event ->
                CrashEventItem(event)
            }
        }
    }
}

@Composable
fun CrashEventItem(event: CrashEvent) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Detected: ${formatTimestamp(event.detectedAtMillis)}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Acceleration: ${event.accelerationMagnitude}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Audio Amplitude: ${event.audioAmplitude}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

//temporary until we have real sensor data with real date
private fun formatTimestamp(millis: Long): String {
    val formatter = SimpleDateFormat(
        "MMM dd, yyyy h:mm a",
        Locale.getDefault()
    )
    return formatter.format(Date(millis))
}