package com.example.noimo.data.remote.auth

import com.example.noimo.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email

class SupabaseAuthDataSource(
    private val client: SupabaseClient
) {
    suspend fun signInTestUser() {
        client.auth.signInWith(Email) {
            email = BuildConfig.TEST_EMAIL
            password = BuildConfig.TEST_PASSWORD
        }
    }
}