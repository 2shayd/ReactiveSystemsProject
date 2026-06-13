package com.example.noimo.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactRemoteDto(
    val id: String,

    @SerialName("user_id")
    val userId: String,

    val email: String
)