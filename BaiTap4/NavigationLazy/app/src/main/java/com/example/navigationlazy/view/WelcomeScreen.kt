package com.example.navigationlazy.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navigationlazy.Controller
import com.example.navigationlazy.R

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {

        Image(
            painter = painterResource(id = R.drawable.jetpack_compose_logo),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier
                .size(216.dp, 233.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.size(340.dp)
        ) {
            Text(
                text = "Navigation",
                style = TextStyle(
                    fontWeight = FontWeight(600),
                    fontSize = 20.sp,
                    lineHeight = 22.4.sp
                ),

                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "is a framework that simplifies the implementation " +
                        "of navigation between different UI components " +
                        "(activities, fragments, or composables) in an app",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color(0xFF4A4646),
                    fontWeight = FontWeight(400),
                    lineHeight = 22.sp
                ),
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
                text = "Push",
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
