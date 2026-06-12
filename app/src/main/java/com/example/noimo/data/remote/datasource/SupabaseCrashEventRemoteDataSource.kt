package com.example.noimo.data.remote.datasource

import com.example.noimo.data.remote.dto.CrashEventRemoteDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order

class SupabaseCrashEventRemoteDataSource(
    private val client: SupabaseClient
) : CrashEventRemoteDataSource {

    override suspend fun uploadCrashEvents(events: List<CrashEventRemoteDto>) {
        if (events.isEmpty()) return

        client
            .from("crash_events")
            .insert(events)
    }

    override suspend fun getCrashEvents(): List<CrashEventRemoteDto> {
        return client
            .from("crash_events")
            .select {
                order("detected_at_millis", Order.DESCENDING)
            }
            .decodeList<CrashEventRemoteDto>()
    }
}