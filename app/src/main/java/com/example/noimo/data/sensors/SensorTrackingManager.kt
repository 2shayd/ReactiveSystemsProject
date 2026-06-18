package com.example.noimo.data.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

// Vitoria worked on this.
// Tracks real accelerometer values from the device.
class SensorTrackingManager(
    context: Context,
    private val onAccelerationChanged: (Float) -> Unit
) : SensorEventListener {

    private val sensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val accelerometer =
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    fun startTracking() {
        accelerometer?.let {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    fun stopTracking() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {

            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val magnitude = sqrt(x * x + y * y + z * z)

            if (magnitude > 12f) {
                onAccelerationChanged(magnitude)
            }        }
    }

    override fun onAccuracyChanged(
        sensor: Sensor?,
        accuracy: Int
    ) {
        // No action needed.
    }
}