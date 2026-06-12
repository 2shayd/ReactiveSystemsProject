package com.example.noimo.data.remote.mapper

import com.example.noimo.data.local.entity.ContactEntity
import com.example.noimo.data.remote.dto.ContactRemoteDto
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ContactRemoteMapperTest {

    @Test
    fun contactEntityMapsToRemoteDto() {
        val entity = ContactEntity(
            id = "550e8400-e29b-41d4-a716-446655440000",
            email = "john@example.com",
            storedLocallyAtMillis = 1000L
        )

        val dto = entity.toRemoteDto(
            userId = "123e4567-e89b-12d3-a456-426614174000"
        )

        assertEquals("550e8400-e29b-41d4-a716-446655440000", dto.id)
        assertEquals("123e4567-e89b-12d3-a456-426614174000", dto.userId)
        assertEquals("john@example.com", dto.email)
    }

    @Test
    fun contactRemoteDtoMapsToEntity() {
        val dto = ContactRemoteDto(
            id = "550e8400-e29b-41d4-a716-446655440000",
            userId = "123e4567-e89b-12d3-a456-426614174000",
            email = "john@example.com"
        )

        val entity = dto.toEntity(
            storedLocallyAtMillis = 2000L
        )

        assertEquals("550e8400-e29b-41d4-a716-446655440000", entity.id)
        assertEquals("john@example.com", entity.email)
        assertEquals(2000L, entity.storedLocallyAtMillis)
        assertTrue(entity.synced)
    }
}