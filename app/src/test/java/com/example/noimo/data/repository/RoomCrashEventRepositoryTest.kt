package com.example.noimo.data.repository

import com.example.noimo.data.local.CrashEventDao
import com.example.noimo.data.local.CrashEventEntity
import com.example.noimo.domain.CrashEvent
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RoomCrashEventRepositoryTest {

    private val dao = mockk<CrashEventDao>()
    private val repository = RoomCrashEventRepository(dao)

    @Test
    fun observeCrashEvents_mapEntitiesToDomainModels() = runTest {
        val entity = CrashEventEntity(
            id = "event-1",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35f,
            audioAmplitude = 0.9f,
            storedLocallyAtMillis = 1100L,
            synced = false
        )

        every { dao.observeAll() } returns flowOf(listOf(entity))

        val result = repository.observeCrashEvents().first()

        assertEquals(1, result.size)
        assertEquals("event-1", result[0].id)
        assertEquals(1000L, result[0].detectedAtMillis)
        assertEquals(35f, result[0].accelerationMagnitude, 0.001f)
        assertEquals(0.9f, result[0].audioAmplitude, 0.001f)
    }

    @Test
    fun saveCrashEvent_insertsEntityIntoDao() = runTest {
        val eventSlot = slot<CrashEventEntity>()

        coEvery { dao.insert(capture(eventSlot)) } returns Unit

        val event = CrashEvent(
            id = "event-1",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35f,
            audioAmplitude = 0.9f
        )

        repository.saveCrashEvent(event)

        coVerify { dao.insert(any()) }

        val insertedEntity = eventSlot.captured

        assertEquals("event-1", insertedEntity.id)
        assertEquals(1000L, insertedEntity.detectedAtMillis)
        assertEquals(35f, insertedEntity.accelerationMagnitude, 0.001f)
        assertEquals(0.9f, insertedEntity.audioAmplitude, 0.001f)
        assertFalse(insertedEntity.synced)
    }

    @Test
    fun getUnsyncedEvents_mapsEntitiesToDomainModels() = runTest {
        val entity = CrashEventEntity(
            id = "event-1",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35f,
            audioAmplitude = 0.9f,
            storedLocallyAtMillis = 1100L,
            synced = false
        )

        coEvery { dao.getUnsyncedEvents() } returns listOf(entity)

        val result = repository.getUnsyncedEvents()

        assertEquals(1, result.size)
        assertEquals("event-1", result[0].id)
        assertEquals(1000L, result[0].detectedAtMillis)
    }

    @Test
    fun markSynced_delegatesToDao() = runTest {
        val ids = listOf("event-1", "event-2")

        coEvery { dao.markSynced(ids) } returns Unit

        repository.markSynced(ids)

        coVerify { dao.markSynced(ids) }
    }
}