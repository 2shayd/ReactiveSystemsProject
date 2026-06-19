package com.example.noimo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noimo.domain.CrashEventRepository

class CrashEventViewModelFactory(
    private val repository: CrashEventRepository
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CrashEventViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CrashEventViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}