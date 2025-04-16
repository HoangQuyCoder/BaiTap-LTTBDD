package com.example.uthsmarttasks.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Description
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Filled.Home)
    object Date : BottomNavItem("date", "Date", Icons.Filled.DateRange)
    object Setting : BottomNavItem("setting", "Setting", Icons.Filled.Settings)
    object Document : BottomNavItem("document", "Document", Icons.Outlined.Description)
}