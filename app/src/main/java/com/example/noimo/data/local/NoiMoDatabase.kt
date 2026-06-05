package com.example.noimo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CrashEventEntity::class],
    version = 1
)
abstract class NoiMoDatabase : RoomDatabase() {
    abstract fun crashEventDao(): CrashEventDao
}