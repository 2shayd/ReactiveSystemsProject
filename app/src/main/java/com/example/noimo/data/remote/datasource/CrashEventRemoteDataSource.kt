package com.example.noimo.data.remote.datasource

import com.example.noimo.data.remote.dto.CrashEventRemoteDto

interface CrashEventRemoteDataSource {
    suspend fun uploadCrashEvents(events: List<CrashEventRemoteDto>)
}