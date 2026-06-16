package com.example.noimo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noimo.viewmodel.ProfileViewModel

// Vitoria worked on this screen.
// ProfileScreen reads profile state from ProfileViewModel using StateFlow.
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel()
) {
    val name by profileViewModel.name.collectAsState()
    val phone by profileViewModel.phone.collectAsState()
    val email by profileViewModel.email.collectAsState()
    val sensorTrackingEnabled by profileViewModel.sensorTrackingEnabled.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        ProfileInfoCard("Name", name)
        ProfileInfoCard("Phone", phone)
        ProfileInfoCard("Email", email)

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Sensor Tracking",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = if (sensorTrackingEnabled) "Enabled" else "Disabled",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Switch(
                    checked = sensorTrackingEnabled,
                    onCheckedChange = {
                        profileViewModel.setSensorTrackingEnabled(it)
                    }
                )
            }
        }
    }
}

@Composable
fun ProfileInfoCard(
    label: String,
    value: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium
            )

            Divider(
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}