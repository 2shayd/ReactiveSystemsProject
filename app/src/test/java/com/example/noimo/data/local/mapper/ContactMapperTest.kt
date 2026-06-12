package com.example.noimo.data.local.mapper

import com.example.noimo.data.local.entity.ContactEntity
import com.example.noimo.domain.Contact
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.junit.Test

class ContactMapperTest {

    @Test
    fun contactEntityMapsToDomain() {
        val entity = ContactEntity(
            id = "contact-1",
            email = "john@example.com",
            storedLocallyAtMillis = 1000L
        )

        val contact = entity.toDomain()

        assertEquals("contact-1", contact.id)
        assertEquals("john@example.com", contact.email)
    }

    @Test
    fun contactMapsToEntity() {
        val contact = Contact(
            id = "contact-1",
            email = "john@example.com"
        )

        val entity = contact.toEntity(
            storedLocallyAtMillis = 1000L
        )

        assertEquals("contact-1", entity.id)
        assertEquals("john@example.com", entity.email)
        assertEquals(1000L, entity.storedLocallyAtMillis)
        assertFalse(entity.synced)
    }
}