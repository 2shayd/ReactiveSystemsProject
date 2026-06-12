// Jesse worked on this
package com.example.noimo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noimo.data.local.dao.CrashEventDao
import com.example.noimo.data.local.entity.CrashEventEntity

@Database(
    entities = [CrashEventEntity::class],
    version = 1
)
abstract class NoiMoDatabase : RoomDatabase() {
    abstract fun crashEventDao(): CrashEventDao
}