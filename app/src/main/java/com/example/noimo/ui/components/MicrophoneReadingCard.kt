package com.example.noimo.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import kotlin.math.abs
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
class MicrophoneReadingCard {
    private val buffer = ShortArray(1024)

    fun getAmplitude(): Int{
        var max = 0
        for (s in buffer) {
            val v = abs(s.toInt())
            if (v > max) max = v
        }
        return max
    }
}
@Composable
fun MicrophoneReadingCard(
    amplitude: Int,
    modifier: Modifier = Modifier
) {
   Card(
       modifier = modifier,
       shape = RoundedCornerShape(16.dp),
       colors = CardDefaults.cardColors()
   ) {
       Column(Modifier.padding(16.dp)) {
           Text(
               text = "Mic Amplitude: $amplitude",
               style = MaterialTheme.typography.titleMedium
           )
       }
   }
}