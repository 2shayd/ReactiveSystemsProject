package com.example.noimo.ui.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
//shayla worked on this
@Composable
fun NoiMoBottomBar(
    navController: NavHostController
) {

    val currentRoute =
        navController.currentBackStackEntryAsState()
            .value
            ?.destination
            ?.route

    NavigationBar {

        NavigationBarItem(
            selected = currentRoute == NoiMoRoute.Home.route,
            onClick = {
                navController.navigate(NoiMoRoute.Home.route)
            },
            label = {
                Text("Home")
            },
            icon = {}
        )

        NavigationBarItem(
            selected = currentRoute == NoiMoRoute.Records.route,
            onClick = {
                navController.navigate(NoiMoRoute.Records.route)
            },
            label = {
                Text("Records")
            },
            icon = {}
        )

        NavigationBarItem(
            selected = currentRoute == NoiMoRoute.ContactList.route,
            onClick = {
                navController.navigate(NoiMoRoute.ContactList.route)
            },
            label = {
                Text("Contacts")
            },
            icon = {}
        )

        NavigationBarItem(
            selected = currentRoute == NoiMoRoute.Profile.route,
            onClick = {
                navController.navigate(NoiMoRoute.Profile.route)
            },
            label = {
                Text("Profile")
            },
            icon = {}
        )
    }
}