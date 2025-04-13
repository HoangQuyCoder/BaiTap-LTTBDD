package com.example.datastoreapplication

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlin.text.toIntOrNull

object ThemePreference {
    private const val THEME_KEY = "theme_color"
    private val Context.dataStore by preferencesDataStore(name = "settings")

    suspend fun saveTheme(context: Context, color: Color) {
        val colorString = color.toArgb().toString()
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(THEME_KEY)] = colorString
        }
    }

    suspend fun getTheme(context: Context): Color {
        val preferences = context.dataStore.data.first()
        val colorString = preferences[stringPreferencesKey(THEME_KEY)]
        return colorString?.toIntOrNull()?.let { Color(it) } ?: Color.White
    }
}