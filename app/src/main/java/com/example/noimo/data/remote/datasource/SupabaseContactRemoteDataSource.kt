package com.example.noimo.data.remote.datasource

import com.example.noimo.data.remote.dto.ContactRemoteDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order

class SupabaseContactRemoteDataSource(
    private val client: SupabaseClient
) : ContactRemoteDataSource {
    override suspend fun uploadContacts(contacts: List<ContactRemoteDataSource>) {
        if (contacts.isEmpty()) return

        client
            .from("contacts")
            .upsert(contacts)
    }

    override suspend fun getContacts(): List<ContactRemoteDto> {
        return client
            .from("contacts")
            .select {
                order("email", Order.ASCENDING)
            }
            .decodeList<ContactRemoteDto>()
    }

    override suspend fun deleteContact(id: String) {
        client
            .from("contacts")
            .delete {
                filter {
                    eq("id", id)
                }
            }
    }
}