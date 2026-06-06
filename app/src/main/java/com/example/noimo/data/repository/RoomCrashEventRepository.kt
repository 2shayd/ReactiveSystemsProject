// Jesse worked on this
package com.example.noimo.data.repository

import com.example.noimo.data.local.CrashEventDao
import com.example.noimo.data.local.toDomain
import com.example.noimo.data.local.toEntity
import com.example.noimo.domain.CrashEvent
import com.example.noimo.domain.CrashEventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomCrashEventRepository(
    private val dao: CrashEventDao
) : CrashEventRepository {

    override fun observeCrashEvents(): Flow<List<CrashEvent>> {
        return dao.observeAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveCrashEvent(event: CrashEvent) {
        dao.insert(event.toEntity())
    }

    override suspend fun getUnsyncedEvents(): List<CrashEvent> {
        return dao.getUnsyncedEvents().map { it.toDomain() }
    }

    override suspend fun markSynced(ids: List<String>) {
        dao.markSynced(ids)
    }
}