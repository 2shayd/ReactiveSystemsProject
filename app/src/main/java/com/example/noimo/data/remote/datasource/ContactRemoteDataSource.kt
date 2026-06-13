package com.example.noimo.data.remote.datasource

import com.example.noimo.data.remote.dto.ContactRemoteDto

interface ContactRemoteDataSource {
    suspend fun uploadContacts(contacts: List<ContactRemoteDataSource>)
    suspend fun getContacts(): List<ContactRemoteDto>
    suspend fun deleteContact(id: String)
}