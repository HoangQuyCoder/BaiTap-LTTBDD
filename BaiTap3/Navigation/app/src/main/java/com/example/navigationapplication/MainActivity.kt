package com.example.navigationapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            Controller()
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White).padding(vertical = 8.dp)
    ) {
        Text(
            text = "$title Detail",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(6.dp)
                .height(40.dp),
            color = Color(0xFF2196F3),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Button(
            onClick = { navController.navigate("components") },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 6.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Back",
                tint = Color(0xFF2196F3)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = buildAnnotatedString {
                    append("The ")

                    withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append("quick")
                    }

                    append(" ")

                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFFB87333),
                            fontSize = 44.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("B")
                    }

                    withStyle(style = SpanStyle(color = Color(0xFFB87333), fontSize = 44.sp)) {
                        append("rown")
                    }

                    append("\n fox ")

                    withStyle(style = SpanStyle(letterSpacing = 6.sp)) {
                        append("jumps ")
                    }

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("over")
                    }

                    append("\n")

                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("the")
                    }

                    append(" ")

                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                        append("lazy")
                    }

                    append(" dog.")
                },
                modifier = Modifier.padding(16.dp),
                fontSize = 36.sp,
                fontWeight = FontWeight.Normal,
                style = TextStyle(lineHeight = 50.sp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 0.5.dp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun UIComponentsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White).verticalScroll(rememberScrollState()).padding(vertical = 8.dp)
    ) {
        Text(
            text = "UI Components List",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
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
    navController: NavController, title: String, components: List<Pair<String, String>>
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

        components.forEach { (name, description) ->
            ComponentCard(name, description) {
                navController.navigate("detail/$name")
            }
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
            .padding(vertical = 8.dp)
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

        Image(
            painter = painterResource(id = R.drawable.jetpack_compose_logo),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier
                .size(216.dp, 233.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Jetpack Compose",
                style = TextStyle(
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp,
                    lineHeight = 22.sp
                ),
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Jetpack Compose is a modern UI toolkit for " +
                        "building native Android applications using " +
                        "a declarative programming approach.",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight(400),
                    lineHeight = 22.sp
                ),
                modifier = Modifier
                    .size(300.dp, 60.dp)
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
    Controller()
}