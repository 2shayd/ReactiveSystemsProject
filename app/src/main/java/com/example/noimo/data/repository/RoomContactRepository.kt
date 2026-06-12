package com.example.noimo.data.repository

import com.example.noimo.data.local.dao.ContactDao
import com.example.noimo.data.local.mapper.toDomain
import com.example.noimo.data.local.mapper.toEntity
import com.example.noimo.domain.Contact
import com.example.noimo.domain.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomContactRepository(
    private val dao: ContactDao
) : ContactRepository {

    override fun observeContacts(): Flow<List<Contact>> {
        return dao.observeAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun saveContact(contact: Contact) {
        dao.insert(contact.toEntity())
    }

    override suspend fun getUnsyncedContacts(): List<Contact> {
        return dao.getUnsyncedContacts().map { it.toDomain() }
    }

    override suspend fun markSynced(ids: List<String>) {
        dao.markSynced(ids)
    }

    override suspend fun deleteContact(id: String) {
        dao.deleteById(id)
    }
}