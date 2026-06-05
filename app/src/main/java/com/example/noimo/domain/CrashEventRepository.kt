package com.example.noimo.domain

import kotlinx.coroutines.flow.Flow

interface CrashEventRepository {
    fun observeCrashEvents(): Flow<List<CrashEvent>>
    suspend fun saveCrashEvent(event: CrashEvent)
    suspend fun getUnsyncedEvents(): List<CrashEvent>
    suspend fun markSynced(ids: List<String>)
}