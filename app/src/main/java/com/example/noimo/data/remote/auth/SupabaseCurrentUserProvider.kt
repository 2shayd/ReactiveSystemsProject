package com.example.noimo.data.remote.auth

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth

class SupabaseCurrentUserProvider(
    private val client: SupabaseClient
) : CurrentUserProvider {

    override fun getCurrentUserId(): String? {
        return client.auth.currentUserOrNull()?.id
    }
}