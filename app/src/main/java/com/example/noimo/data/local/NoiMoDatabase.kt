// Jesse worked on this
package com.example.noimo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noimo.data.local.dao.ContactDao
import com.example.noimo.data.local.dao.CrashEventDao
import com.example.noimo.data.local.entity.ContactEntity
import com.example.noimo.data.local.entity.CrashEventEntity

@Database(
    entities = [
        CrashEventEntity::class,
        ContactEntity::class
               ],
    version = 2
)
abstract class NoiMoDatabase : RoomDatabase() {
    abstract fun crashEventDao(): CrashEventDao
    abstract fun contactDao(): ContactDao
}