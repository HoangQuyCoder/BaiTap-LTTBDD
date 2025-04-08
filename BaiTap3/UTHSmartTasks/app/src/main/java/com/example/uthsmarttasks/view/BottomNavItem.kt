package com.example.uthsmarttasks.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Description
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.uthsmarttasks.R

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Filled.Home)
    object Date : BottomNavItem("date", "Date", Icons.Filled.DateRange)
    object Setting : BottomNavItem("setting", "Setting", Icons.Filled.Settings)
    object Document : BottomNavItem("document", "Document",Icons.Outlined.Description )

    companion object {
        fun createNewItem(route: String, title: String, icon: ImageVector): BottomNavItem {
            return NewItem(route, title, icon)
        }
    }

    class NewItem(route: String, title: String, icon: ImageVector) : BottomNavItem(route, title, icon)
}
