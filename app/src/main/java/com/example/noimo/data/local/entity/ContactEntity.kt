package com.example.noimo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey val id: String,
    val email: String,
    val storedLocallyAtMillis: Long,
    val synced: Boolean = false
)