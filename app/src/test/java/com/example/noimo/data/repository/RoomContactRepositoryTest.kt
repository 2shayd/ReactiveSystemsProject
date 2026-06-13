package com.example.noimo.data.repository

import com.example.noimo.data.local.dao.ContactDao
import com.example.noimo.data.local.entity.ContactEntity
import com.example.noimo.domain.Contact
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

class RoomContactRepositoryTest {

    private val dao = mockk<ContactDao>()
    private val repository = RoomContactRepository(dao)

    @Test
    fun observeContacts_mapsEntitiesToDomainModels() {
        runTest {
            val entity = ContactEntity(
                id = "contact-1",
                email = "john@example.com",
                storedLocallyAtMillis = 1000L
            )

            every { dao.observeAll() } returns flowOf(listOf(entity))

            val result = repository.observeContacts().first()

            assertEquals(1, result.size)
            assertEquals("contact-1", result[0].id)
            assertEquals("john@example.com", result[0].email)
        }
    }

    @Test
    fun saveContact_insertEntityIntoDao() {
        runTest {
            val entitySlot = slot<ContactEntity>()

            coEvery { dao.insert(capture(entitySlot)) } returns Unit

            val contact = Contact(
                id = "contact-1",
                email = "john@example.com"
            )

            repository.saveContact(contact)

            coVerify { dao.insert(any()) }

            val insertedEntity = entitySlot.captured

            assertEquals("contact-1", insertedEntity.id)
            assertEquals("john@example.com", insertedEntity.email)
            assertFalse(insertedEntity.synced)
        }
    }

    @Test
    fun getUnsyncedContacts_mapsEntitiesToDomainModels() {
        runTest {
            val entity = ContactEntity(
                id = "contact-1",
                email = "john@example.com",
                storedLocallyAtMillis = 1000L
            )

            coEvery { dao.getUnsyncedContacts() } returns listOf(entity)

            val result = repository.getUnsyncedContacts()

            assertEquals(1, result.size)
            assertEquals("contact-1", result[0].id)
            assertEquals("john@example.com", result[0].email)
        }
    }

    @Test
    fun markSynced_delegatesToDao() {
        runTest {
            val ids = listOf("contact-1", "contact-2")

            coEvery { dao.markSynced(ids) } returns Unit

            repository.markSynced(ids)

            coVerify { dao.markSynced(ids) }
        }
    }

    @Test
    fun deleteContact_delegatesToDao() {
        runTest {
            coEvery { dao.deleteById("contact-1") } returns Unit

            repository.deleteContact("contact-1")

            coVerify { dao.deleteById("contact-1") }
        }
    }
}