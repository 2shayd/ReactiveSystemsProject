// Jesse worked on this
package com.example.noimo.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class CrashEventDaoTest {

    private lateinit var database: NoiMoDatabase
    private lateinit var dao: CrashEventDao

    // Uses memory to test, NOT disk database
    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(
            context,
            NoiMoDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = database.crashEventDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertEvent_canBeReadBack() = runTest {
        val event = CrashEventEntity(
            id = "event-1",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.0,
            audioAmplitude = 0.9,
            latitude = 47.6062,
            longitude = -122.3321,
            storedLocallyAtMillis = 1100L,
            synced = false
        )

        dao.insert(event)

        val events = dao.observeAll().first()

        assertEquals(1, events.size)
        assertEquals(event, events[0])
    }

    @Test
    fun observeAll_returnsNewestFirst() = runTest {
        val olderEvent = CrashEventEntity(
            id = "event-1",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.0,
            audioAmplitude = 0.9,
            storedLocallyAtMillis = 1100L,
            latitude = 47.6062,
            longitude = -122.3321,
            synced = false
        )

        val newerEvent = CrashEventEntity(
            id = "event-2",
            detectedAtMillis = 3000L,
            accelerationMagnitude = 35.0,
            audioAmplitude = 0.9,
            storedLocallyAtMillis = 3100L,
            latitude = 47.6062,
            longitude = -122.3321,
            synced = false
        )

        dao.insert(olderEvent)
        dao.insert(newerEvent)

        val events = dao.observeAll().first()

        assertEquals("event-2", events[0].id)
        assertEquals("event-1", events[1].id)
    }

    @Test
    fun getUnsyncedEvents_returnsOnlyUnsyncedEvents() = runTest {
        val unsyncedEvent = CrashEventEntity(
            id = "event-1",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.0,
            audioAmplitude = 0.9,
            storedLocallyAtMillis = 1100L,
            latitude = 47.6062,
            longitude = -122.3321,
            synced = false
        )

        val syncedEvent = CrashEventEntity(
            id = "event-2",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.0,
            audioAmplitude = 0.9,
            latitude = 47.6062,
            longitude = -122.3321,
            storedLocallyAtMillis = 1100L,
            synced = true
        )

        dao.insert(unsyncedEvent)
        dao.insert(syncedEvent)

        val result = dao.getUnsyncedEvents()

        assertEquals(1, result.size)
        assertEquals("event-1", result[0].id)
        assertTrue(!result[0].synced)
    }

    @Test
    fun markSynced_updatesMatchingEvents() = runTest {
        val event = CrashEventEntity(
            id = "event-1",
            detectedAtMillis = 1000L,
            accelerationMagnitude = 35.0,
            audioAmplitude = 0.9,
            latitude = 47.6062,
            longitude = -122.3321,
            storedLocallyAtMillis = 1100L,
            synced = false
        )

        dao.insert(event)
        dao.markSynced(listOf("event-1"))

        val unsyncedEvents = dao.getUnsyncedEvents()

        assertTrue(unsyncedEvents.isEmpty())
    }
}