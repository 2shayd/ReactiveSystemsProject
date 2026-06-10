package com.example.noimo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import com.example.noimo.R

@Composable
fun HomeScreen(navController: NavController) {
Column() {
    Image(
        painter = painterResource(R.drawable.rectangle_frame),
        contentDescription = "Home screen Frame",
        modifier = Modifier.size(360.dp)
    )
    Image(
        painter = painterResource(R.drawable.decibel_graphic),
        contentDescription = "Decibel Graphic",
        modifier = Modifier.size(32.dp)
    )
    Image(
        painter = painterResource(R.drawable.noimo_logo),
        contentDescription = "Microphone graphic",
        modifier = Modifier.size(32.dp)
    )
    Image(
        painter = painterResource(R.drawable.accelerometer_graphic),
        contentDescription = "Accelerometer Graphic",
        modifier = Modifier.size(32.dp)
    )
    Row(
        modifier = Modifier
        .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically

    ){

        Image(
            painter = painterResource(R.drawable.home_button),
            contentDescription = "Home Button",
            modifier = Modifier.size(24.dp)
                .clickable {
                    navController.navigate("home")
                }

        )
        Image(
            painter = painterResource(R.drawable.records_button),
            contentDescription = "Records Button",
            modifier = Modifier.size(24.dp)
                .align(Alignment.CenterVertically)
                .clickable{
                    navController.navigate("records")
                }
        )
        Image(
            painter = painterResource(R.drawable.profile_button),
            contentDescription = "Profile Button",
            modifier = Modifier.size(24.dp)
                .align(Alignment.CenterVertically)
                .clickable{
                    navController.navigate("profile")
                }
        )
    }

}



}
