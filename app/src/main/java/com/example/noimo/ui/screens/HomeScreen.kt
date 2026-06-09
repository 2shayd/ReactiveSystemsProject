package com.example.noimo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.noimo.R


@Composable
fun HomeScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Welcome to NoiMo!")

        Image(
            painter = painterResource(id = R.drawable.decibel_graphic),
            contentDescription = "Decibel",
            modifier = Modifier.padding(16.dp) 
        )
        Image(
            painter = painterResource(id = R.drawable.noimo_logo),
            contentDescription = "Microphone",
            modifier = Modifier.padding(16.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.accelerometer_graphic),
            contentDescription = "Accelerometer",
            modifier = Modifier.padding(16.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.rectangle_frame),
            contentDescription = "Frame",
            modifier = Modifier.padding(16.dp)
        )
    }

}



