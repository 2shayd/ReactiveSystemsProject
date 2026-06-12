package com.example.noimo.data.repository

import com.example.noimo.data.local.dao.CrashEventDao
import com.example.noimo.data.local.entity.CrashEventEntity
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
            accelerationMagnitude = 35.0,
            audioAmplitude = 0.9,
            latitude = 47.6062,
            longitude = -122.3321,
            storedLocallyAtMillis = 1100L,
            synced = false
        )

        every { dao.observeAll() } returns flowOf(listOf(entity))

        val result = repository.observeCrashEvents().first()

        assertEquals(1, result.size)
        assertEquals("event-1", result[0].id)
        assertEquals(1000L, result[0].detectedAtMillis)
        assertEquals(35.0, result[0].accelerationMagnitude, 0.001)
        assertEquals(0.9, result[0].audioAmplitude, 0.001)
        assertEquals(47.6062, result[0].latitude!!, 0.001)
        assertEquals(-122.3321, result[0].longitude!!, 0.001)
    }

    @Test
    fun saveCrashEvent_insertsEntityIntoDao() = runTest {
        val eventSlot = slot<CrashEventEntity>()

        coEvery { dao.insert(capture(eventSlot)) } returns Unit

        val event = CrashEvent(
            id = "event-1",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.0,
            audioAmplitude = 0.9,
            latitude = 47.6062,
            longitude = -122.3321,
        )

        repository.saveCrashEvent(event)

        coVerify { dao.insert(any()) }

        val insertedEntity = eventSlot.captured

        assertEquals("event-1", insertedEntity.id)
        assertEquals(1000L, insertedEntity.detectedAtMillis)
        assertEquals(35.0, insertedEntity.accelerationMagnitude, 0.001)
        assertEquals(0.9, insertedEntity.audioAmplitude, 0.001)
        assertEquals(47.6062, insertedEntity.latitude!!, 0.001)
        assertEquals(-122.3321, insertedEntity.longitude!!, 0.001)
        assertFalse(insertedEntity.synced)
    }

    @Test
    fun getUnsyncedEvents_mapsEntitiesToDomainModels() = runTest {
        val entity = CrashEventEntity(
            id = "event-1",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.0,
            audioAmplitude = 0.9,
            storedLocallyAtMillis = 1100L,
            latitude = 47.6062,
            longitude = -122.3321,
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