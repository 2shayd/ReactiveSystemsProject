package com.example.noimo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noimo.viewmodel.ContactViewModel
import com.example.noimo.viewmodel.EmergencyContact

// Vitoria worked on this.
// ContactListScreen reads emergency contacts from ContactViewModel using StateFlow.
@Composable
fun ContactListScreen(
    contactViewModel: ContactViewModel = viewModel()
) {
    val contacts by contactViewModel.contacts.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Contact List",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = {
                    // TODO: Add new contact later
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Contact"
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(contacts) { contact ->
                ContactItem(contact)
            }
        }
    }
}

@Composable
fun ContactItem(contact: EmergencyContact) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Relationship: ${contact.relationship}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Phone: ${contact.phone}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}