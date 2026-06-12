package com.example.noimo.domain

import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun observeContacts(): Flow<List<Contact>>
    suspend fun saveContact(contact: Contact)
    suspend fun getUnsyncedContacts(): List<Contact>
    suspend fun markSynced(ids: List<String>)
    suspend fun deleteContact(id: String)
}