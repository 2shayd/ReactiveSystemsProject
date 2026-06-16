package com.example.noimo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// Vitoria worked on this.
// Holds emergency contact data using StateFlow.
class ContactViewModel : ViewModel() {

    private val _contacts = MutableStateFlow(
        listOf(
            EmergencyContact(
                name = "Jane Doe",
                relationship = "Mother",
                phone = "(425) 555-1234"
            ),
            EmergencyContact(
                name = "John Doe",
                relationship = "Father",
                phone = "(206) 555-5678"
            ),
            EmergencyContact(
                name = "Mike Smith",
                relationship = "Friend",
                phone = "(253) 555-9999"
            )
        )
    )

    val contacts = _contacts.asStateFlow()
}

data class EmergencyContact(
    val name: String,
    val relationship: String,
    val phone: String
)