package com.example.noimo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noimo.R

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to NoiMo!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Main Visualizations
        Image(
            painter = painterResource(id = R.drawable.decibel_graphic),
            contentDescription = "Decibel",
            modifier = Modifier.padding(8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.noimo_logo),
            contentDescription = "Microphone",
            modifier = Modifier.padding(8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.accelerometer_graphic),
            contentDescription = "Accelerometer",
            modifier = Modifier.padding(8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.rectangle_frame),
            contentDescription = "Frame",
            modifier = Modifier.padding(8.dp)
        )

        // Navigation Buttons with imprinted images
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Home Button
            Button(onClick = { navController.navigate("Home") }) {
                Image(
                    painter = painterResource(id = R.drawable.home_button),
                    contentDescription = "Home",
                    modifier = Modifier.size(24.dp) // Adjust size as needed
                )
            }

            // Records Button
            Button(onClick = { navController.navigate("Records") }) {
                Image(
                    painter = painterResource(id = R.drawable.records_button),
                    contentDescription = "Records",
                    modifier = Modifier.size(24.dp)
                )
            }

            // Profile Button
            Button(onClick = { navController.navigate("Profile") }) {
                Image(
                    painter = painterResource(id = R.drawable.profile_button),
                    contentDescription = "Profile",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
