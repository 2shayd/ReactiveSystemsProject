package com.example.noimo.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.noimo.data.local.NoiMoDatabase
import com.example.noimo.data.local.entity.ContactEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class ContactDaoTest {

    private lateinit var database: NoiMoDatabase
    private lateinit var dao: ContactDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(
            context,
            NoiMoDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = database.contactDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertContact_canBeReadBack() {
        runTest {
            val contact = ContactEntity(
                id = "contact-1",
                email = "john@example.com",
                storedLocallyAtMillis = 1000L
            )

            dao.insert(contact)

            val contacts = dao.observeAll().first()

            assertEquals(1, contacts.size)
            assertEquals(contact, contacts[0])
        }
    }

    @Test
    fun observeAll_returnsContactsOrderdedByEmail() {
        runTest {
            val second = ContactEntity(
                id = "contact-2",
                email = "zach@example.com",
                storedLocallyAtMillis = 1000L
            )

            val first = ContactEntity(
                id = "contact-1",
                email = "alice@example.com",
                storedLocallyAtMillis = 1000L
            )

            dao.insert(second)
            dao.insert(first)

            val contacts = dao.observeAll().first()

            assertEquals("alice@example.com", contacts[0].email)
            assertEquals("zach@example.com", contacts[1].email)
        }
    }

    @Test
    fun getUnsyncedContacts_returnsOnlyUnsyncedContacts() {
        runTest {
            val unsynced = ContactEntity(
                id = "contact-1",
                email = "john@example.com",
                storedLocallyAtMillis = 1000L
            )

            val synced = ContactEntity(
                id = "contact-2",
                email = "alice@example.com",
                storedLocallyAtMillis = 1000L,
                synced = true
            )

            dao.insert(unsynced)
            dao.insert(synced)

            val result = dao.getUnsyncedContacts()

            assertEquals(1, result.size)
            assertEquals("contact-1", result[0].id)
            assertEquals("john@example.com", result[0].email)
        }
    }

    @Test
    fun markSynced_updatesMatchingContacts() {
        runTest {
            val contact = ContactEntity(
                id = "contact-1",
                email = "john@example.com",
                storedLocallyAtMillis = 1000L
            )

            dao.insert(contact)

            dao.markSynced(listOf("contact-1"))

            val unsyncedContacts = dao.getUnsyncedContacts()

            assertTrue(unsyncedContacts.isEmpty())
        }
    }

    @Test
    fun deleteById_removesMatchingContact() {
        runTest {
            val contact = ContactEntity(
                id = "contact-1",
                email = "john@example.com",
                storedLocallyAtMillis = 1000L
            )

            dao.insert(contact)

            dao.deleteById("contact-1")

            val contacts = dao.observeAll().first()

            assertTrue(contacts.isEmpty())
        }
    }
}