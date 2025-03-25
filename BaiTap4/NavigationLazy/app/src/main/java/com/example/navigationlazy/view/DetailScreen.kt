package com.example.navigationlazy.view

import android.R.attr.lineHeight
import android.R.attr.onClick
import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.navigationlazy.Controller
import com.example.navigationlazy.R

@Composable
fun DetailScreen(title: String, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Button(
                onClick = {navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopStart),
                colors = ButtonDefaults.buttonColors(Color(0xFF2196F3)),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Text(
                text = "Detail",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp),
        ) {
            Text(
                text = "“${title}”",
                fontSize = 18.sp,
                fontWeight = FontWeight(400),
                color = Color.Black,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(36.dp))

            // Quote Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(444.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF2196F3)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontSize = 36.sp
                            )
                        ) {
                            append("\"The only way to do great work\nis to love what you do.\"\n")
                        }

                        withStyle(
                            style = SpanStyle(
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight(400),
                                fontSize = 16.sp
                            )
                        ) {
                            append("Steve Jobs")
                        }
                    },
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 36.sp,
                        fontWeight = FontWeight(450),
                        color = Color.Black,
                        lineHeight = 48.sp
                    ),
                    modifier = Modifier
                        .padding(36.dp)
                        .align(Alignment.TopCenter)
                )


                Text(
                    text = "http://quoes.thisgrandpablogs.com/",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomCenter)
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = { navController.navigate("welcome") },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF007BFF)
                ),
                modifier = Modifier
                    .size(315.dp, 53.dp)

            ) {
                Text(
                    text = "BACK TO ROOT",
                    color = Color.White,
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    Controller()
}

