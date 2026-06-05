// jesse worked on this
package com.example.noimo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CrashEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: CrashEventEntity)

    @Query("SELECT * FROM crash_events ORDER BY detectedAtMillis DESC")
    fun observeAll(): Flow<List<CrashEventEntity>>

    @Query("SELECT * FROM crash_events WHERE synced = 0")
    suspend fun getUnsyncedEvents(): List<CrashEventEntity>

    @Query("UPDATE crash_events SET synced = 1 WHERE id IN (:ids)")
    suspend fun markSynced(ids: List<String>)
}