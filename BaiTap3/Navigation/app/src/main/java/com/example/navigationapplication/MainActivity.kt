package com.example.navigationapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationapplication.ui.theme.NavigationApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationApplicationTheme {
                Controller()
            }
        }
    }
}

@Composable
fun Controller() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("components") { UIComponentsScreen(navController) }
        composable("detail/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Detail"
            DetailScreen(title, navController)
        }
    }
}

@Composable
fun DetailScreen(title: String, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 24.dp),

        ) {
        Box(
            modifier = Modifier
                .padding(top = 20.dp),

            ) {
            Text(
                text = "$title Detail",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = Color(0xFF2196F3),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Button(
                onClick = {  navController.navigate("components")},
                modifier = Modifier.height(40.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
            )
            {
                Text(
                    "<", fontSize = 24.sp,
                    modifier = Modifier
                        .height(40.dp),
                    color = Color(0xFF2196F3)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.image),
                contentDescription = null,
                modifier = Modifier
                    .size(354.dp, 240.dp)
                    .offset(y = 160.dp)
            )
        }
    }
//        Card(
//            shape = RoundedCornerShape(8.dp),
//            colors = CardDefaults.cardColors(containerColor = Color(0xFFFDEEDC)),
//            modifier = Modifier.fillMaxWidth().padding(16.dp)
//        ) {
//            Text(
//                text = "This is the detail screen for $title.",
//                modifier = Modifier.padding(16.dp),
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Normal
//            )
//        }
}


@Composable
fun UIComponentsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "UI Components List",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 28.dp, bottom = 16.dp)
        )

        ComponentSection(
            navController,
            title = "Display",
            components = listOf("Text" to "Displays text", "Image" to "Displays an image")
        )
        ComponentSection(
            navController,
            title = "Input",
            components = listOf(
                "TextField" to "Input field for text",
                "PasswordField" to "Input field for passwords"
            )
        )
        ComponentSection(
            navController,
            title = "Layout",
            components = listOf(
                "Column" to "Arranges elements vertically",
                "Row" to "Arranges elements horizontally"
            )
        )
    }
}

@Composable
fun ComponentSection(
    navController: NavController,
    title: String,
    components: List<Pair<String, String>>
) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
    )

    components.forEach { (name, description) ->
        ComponentCard(name, description) {
            navController.navigate("detail/$name")
        }
    }
}

@Composable
fun ComponentCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8FF)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = description, fontSize = 18.sp, color = Color.Black)
        }
    }
}


@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {

        Box(
            modifier = Modifier
                .size(216.dp, 233.dp)
                .offset(y = 80.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.jetpack_compose_logo),
                contentDescription = "Jetpack Compose Logo",
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(y = -80.dp)
        ) {
            // Tiêu đề
            Text(
                text = "Jetpack Compose",
                fontWeight = FontWeight(500),
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.size(160.dp, 22.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Mô tả
            Text(
                text = "Jetpack Compose is a modern UI toolkit for " +
                        "building native Android applications using " +
                        "a declarative programming approach.",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight(400),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .width(300.dp)
                    .height(80.dp)

            )
        }

        Button(
            onClick = { navController.navigate("components") },
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF007BFF)
            ),
            modifier = Modifier
                .size(315.dp, 53.dp)
        ) {
            Text(
                text = "I'm ready",
                color = Color.White,
                fontWeight = FontWeight(700),
                fontSize = 20.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewWelcomeScreen() {
    NavigationApplicationTheme {
        Controller()
    }
}