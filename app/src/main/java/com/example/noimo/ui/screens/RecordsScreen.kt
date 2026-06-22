package com.example.noimo.ui.screens

import androidx.compose.foundation.clickable
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

// shayla worked on this
@Composable
fun RecordsScreen(
    events: List<CrashEvent>,
    onEventClick: (String) -> Unit
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

        if (events.isEmpty()) {
            Text(
                text = "No crash events recorded yet.",
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyColumn(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                items(events) { event ->
                    CrashEventItem(
                        event = event,
                        onClick = {
                            onEventClick(event.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CrashEventItem(
    event: CrashEvent,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Detected: ${formatTimestamp(event.detectedAtMillis)}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Acceleration: ${event.accelerationMagnitude}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Audio Amplitude: ${event.audioAmplitude}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Tap to view details",
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

private fun formatTimestamp(millis: Long): String {
    val formatter = SimpleDateFormat("MMM dd, yyyy h:mm a", Locale.getDefault())
    return formatter.format(Date(millis))
}