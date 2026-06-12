package com.example.noimo.data.remote.mapper

import com.example.noimo.data.local.entity.ContactEntity
import com.example.noimo.data.remote.dto.ContactRemoteDto

fun ContactEntity.toRemoteDto(userId: String): ContactRemoteDto {
    return ContactRemoteDto(
        id = id,
        userId = userId,
        email = email
    )
}

fun ContactRemoteDto.toEntity(
    storedLocallyAtMillis: Long = System.currentTimeMillis()
): ContactEntity {
    return ContactEntity(
        id = id,
        email = email,
        storedLocallyAtMillis = storedLocallyAtMillis,
        synced = true
    )
}