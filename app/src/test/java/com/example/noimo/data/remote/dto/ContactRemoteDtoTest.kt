package com.example.noimo.data.remote.dto

import junit.framework.TestCase.assertTrue
import kotlinx.serialization.json.Json
import org.junit.Test

class ContactRemoteDtoTest {

    private val json = Json {
        encodeDefaults = true
    }

    @Test
    fun contactRemoteDtoSerializesWithSupabaseColumnNames() {
        val dto = ContactRemoteDto(
            id = "550e8400-e29b-41d4-a716-446655440000",
            userId = "123e4567-e89b-12d3-a456-426614174000",
            email = "john@example.com"
        )

        val serialized = json.encodeToString(
            ContactRemoteDto.serializer(),
            dto
        )

        assertTrue(serialized.contains("\"user_id\""))
    }
}