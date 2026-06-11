package com.example.noimo.ui.navigation

//shayla worked on this
sealed class NoiMoRoute(val route: String) {
    data object Home : NoiMoRoute("home")
    data object Records : NoiMoRoute("records")
    data object IncidentDetail : NoiMoRoute("incident_detail")
    data object Profile : NoiMoRoute("profile")
    data object ContactList : NoiMoRoute("contact_list")
}
