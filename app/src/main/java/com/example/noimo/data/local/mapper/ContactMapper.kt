package com.example.noimo.data.local.mapper

import com.example.noimo.data.local.entity.ContactEntity
import com.example.noimo.domain.Contact

fun ContactEntity.toDomain(): Contact {
    return Contact(
        id = id,
        email = email
    )
}

fun Contact.toEntity(
    storedLocallyAtMillis: Long = System.currentTimeMillis()
): ContactEntity {
    return ContactEntity(
        id = id,
        email = email,
        storedLocallyAtMillis = storedLocallyAtMillis
    )
}