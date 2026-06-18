package com.example.noimo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noimo.data.local.dao.CrashEventDao
import com.example.noimo.data.remote.datasource.CrashEventRemoteDataSource

class SensorViewModelFactory(
    private val crashEventDao: CrashEventDao,
    private val crashEventRemoteDataSource: CrashEventRemoteDataSource,
    private val currentUserProvider: CurrentUserProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SensorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SensorViewModel(
                crashEventDao = crashEventDao,
                crashEventRemoteDataSource = crashEventRemoteDataSource,
                currentUserProvider = currentUserProvider
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}