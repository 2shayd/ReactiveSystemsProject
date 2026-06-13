package com.example.noimo.data.local.entity

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ContactEntityTest {

    @Test
    fun contactEntityStoresValuesCorrectly() {
        val entity = ContactEntity(
            id = "contact-1",
            email = "john@example.com",
            storedLocallyAtMillis = 1000L,
        )

        assertEquals("contact-1", entity.id)
        assertEquals("john@example.com", entity.email)
        assertEquals(1000L, entity.storedLocallyAtMillis)
        assertFalse(entity.synced)
    }

    @Test
    fun contactEntityCopyCanUpdateSyncedValue() {
        val entity = ContactEntity(
            id = "contact-1",
            email = "john@example.com",
            storedLocallyAtMillis = 1000L,
        )

        val updated = entity.copy(synced = true)

        assertTrue(updated.synced)
        assertEquals("contact-1", updated.id)
        assertEquals("john@example.com", updated.email)
    }
}