package com.example.noimo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noimo.ui.screens.ContactListScreen
import com.example.noimo.ui.screens.HomeScreen
import com.example.noimo.ui.screens.IncidentDetailScreen
import com.example.noimo.ui.screens.ProfileScreen
import com.example.noimo.ui.screens.RecordsScreen
import com.example.noimo.viewmodel.CrashEventViewModel
import androidx.compose.runtime.getValue
//shayla worked on this
@Composable
fun NoiMoNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NoiMoRoute.Home.route
    ) {
        composable(NoiMoRoute.Home.route) {
            HomeScreen()
        }

        composable(NoiMoRoute.Records.route) {
            val viewModel: CrashEventViewModel = viewModel()
            val events by viewModel.events.collectAsState()

            RecordsScreen(events = events)
        }

        composable(NoiMoRoute.IncidentDetail.route) {
            IncidentDetailScreen()
        }

        composable(NoiMoRoute.Profile.route) {
            ProfileScreen()
        }

        composable(NoiMoRoute.ContactList.route) {
            ContactListScreen()
        }
    }
}