package com.hacknyu.reclaimlife.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

val navigationItems = listOf(
    NavigationItem(
        title = "Home",
        icon = Icons.Default.Home,
        screen = Screen.Home
    ),
    NavigationItem(
        title = "Community",
        icon = Icons.Default.Person,
        screen = Screen.Community
    ),
    NavigationItem(
        title = "Motivation",
        icon = Icons.Default.Star,
        screen = Screen.Motivation
    ),
    NavigationItem(
        title = "Settings",
        icon = Icons.Default.Settings,
        screen = Screen.Settings
    )
)

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)