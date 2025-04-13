package com.example.datastoreapplication

import android.R.attr.name
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.datastoreapplication.ui.theme.DataStoreApplicationTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            AppNavHost()
        }
    }
}

@Composable
fun AppNavHost(){
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main") {
        composable("main") {MainScreen(navController)}
        composable(
            route = "intro/{color}/{colorName}/{description}",
            arguments = listOf(
                navArgument("color") { type = NavType.IntType },
                navArgument("colorName") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val colorInt = backStackEntry.arguments?.getInt("color") ?: 0xFFFFFFFF.toInt()
            val colorName = backStackEntry.arguments?.getString("colorName") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            IntroScreen(Color(colorInt), colorName, description, navController)
        }
    }
}

@Composable
fun MainScreen( navController: NavController){
    var selectedColor by remember { mutableStateOf(Color.White) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val colorMap = mapOf(
        Color(0xFF87CEFA) to "Blue",
        Color(0xFFDA70D6) to "Pink",
        Color.Black to "Dark",
    )

    // Load saved theme from DataStore
    LaunchedEffect(Unit) {
        selectedColor = ThemePreference.getTheme(context)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = selectedColor) {
        ThemeSettingScreen(
            selectedColor = selectedColor,
            onColorSelected = { selectedColor = it },
            onApply = {
                scope.launch {
                    ThemePreference.saveTheme(context, selectedColor)
                    val colorName = colorMap[selectedColor] ?: "Nullable"
                    val description = "Choosing the right theme sets the tone and personality of your app, enhancing user experience and reinforcing your brand identity"
                    val colorInt = selectedColor.toArgb()

                    navController.navigate(
                        "intro/$colorInt/${Uri.encode(colorName)}/${Uri.encode(description)}"
                    )
                }
            }
        )
    }
}

@Composable
fun ThemeSettingScreen(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    onApply: () -> Unit
) {
    val themeColors = listOf(Color(0xFF87CEFA), Color(0xFFDA70D6), Color.Black)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Setting", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Choosing the right theme sets the tone and personality of your app",
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.SpaceAround) {
            themeColors.forEach { color ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color)
                        .border(
                            width = if (color == selectedColor) 3.dp else 0.dp,
                            color = if (color == selectedColor) Color.Black else Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { onColorSelected(color) }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onApply,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E90FF))
        ) {
            Text("Apply", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    AppNavHost()
}