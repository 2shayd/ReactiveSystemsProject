package com.example.noimo.data.remote.auth

interface CurrentUserProvider {
    fun getCurrentUserId(): String?
}