// Jesse worked on this
package com.example.noimo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// Room Entity for detected crash object.
@Entity(tableName = "crash_events")
data class CrashEventEntity(
    @PrimaryKey val id: String,
    val detectedAtMillis: Long,
    val accelerationMagnitude: Float,
    val audioAmplitude: Float,
    val storedLocallyAtMillis: Long,
    val synced: Boolean = false
)