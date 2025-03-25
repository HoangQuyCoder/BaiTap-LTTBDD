package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasks.view.BottomNavItem
import com.example.uthsmarttasks.view.DateScreen
import com.example.uthsmarttasks.view.FirstIntroduction
import com.example.uthsmarttasks.view.HomeScreen
import com.example.uthsmarttasks.view.MenuScreen
import com.example.uthsmarttasks.view.SecondIntroduction
import com.example.uthsmarttasks.view.SettingScreen
import com.example.uthsmarttasks.view.TaskDetailScreen
import com.example.uthsmarttasks.view.ThirdIntroduction
import com.example.uthsmarttasks.view.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationGraph()
        }
    }
}

@Composable
<<<<<<< HEAD
fun NavigationGraph() {
=======
fun WelcomeScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("firstIntroduction") {
            popUpTo("welcome") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_uth),
            contentDescription = "UTH Smart Tasks",
            modifier = Modifier.size(200.dp, 80.dp)
        )

        Text(
            text = "UTH Smart Tasks",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF006EE9),
//                fontFamily = AppFonts.righteous
            ),
            modifier = Modifier.padding(16.dp)

        )
    }
}

@Composable
fun FirstIntroduction(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicator dots
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == 0) Color(0xFF006EE9) else Color(0xFFEEF5FD)
                            )
                    )
                }
            }

            Text(
                text = "skip",
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                ),
                color = Color(0xFF006EE9),
                modifier = Modifier.clickable {}
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.size(320.dp, 420.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.portfolio_update),
                contentDescription = "Easy Time Management",
                modifier = Modifier
                    .size(320.dp, 260.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Easy Time Management",
                style = TextStyle(
                    fontWeight = FontWeight(500),
                    fontSize = 18.sp,
                ),
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "With management based on priority and daily tasks, " +
                        "it will give you convenience in managing and determining " +
                        "the tasks that must be done first ",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color(0xFF4A4646),
                    fontWeight = FontWeight(400),
                    lineHeight = 22.sp,
                ),
                modifier = Modifier
                    .size(320.dp, 100.dp)
            )
        }

        Button(
            onClick = { navController.navigate("secondIntroduction") },
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF007BFF)
            ),
            modifier = Modifier
                .size(350.dp, 53.dp)
        ) {
            Text(
                text = "Next",
                color = Color.White,
                fontWeight = FontWeight(700),
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun SecondIntroduction(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicator dots
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == 1) Color(0xFF006EE9) else Color(0xFFEEF5FD)
                            ),
                    )
                }
            }

            Text(
                text = "skip",
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                ),
                color = Color(0xFF006EE9),
                modifier = Modifier.clickable { }
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.size(320.dp, 420.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.charts),
                contentDescription = "Increase Work Effectiveness",
                modifier = Modifier
                    .size(320.dp, 260.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Increase Work Effectiveness",
                style = TextStyle(
                    fontWeight = FontWeight(500),
                    fontSize = 18.sp,
                ),
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Time management and the determination of more important " +
                        "tasks will give your job statistics better and always improve",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color(0xFF4A4646),
                    fontWeight = FontWeight(400),
                    lineHeight = 22.sp,
                ),
                modifier = Modifier
                    .size(320.dp, 100.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.navigate("firstIntroduction") },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF007BFF)
                ),
                modifier = Modifier.size(53.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(50.dp)
                )
            }

            Button(
                onClick = { navController.navigate("thirdIntroduction") },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF007BFF)
                ),
                modifier = Modifier.size(300.dp, 53.dp)
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun ThirdIntroduction(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicator dots
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == 2) Color(0xFF006EE9) else Color(0xFFEEF5FD)
                            ),
                    )
                }
            }

            Text(
                text = "skip",
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                ),
                color = Color(0xFF006EE9),
                modifier = Modifier.clickable { }
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.size(320.dp, 420.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.new_message),
                contentDescription = "Reminder Notification",
                modifier = Modifier
                    .size(320.dp, 260.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Reminder Notification",
                style = TextStyle(
                    fontWeight = FontWeight(500),
                    fontSize = 18.sp,
                ),
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "The advantage of this application is that it also provides " +
                        "reminders for you so you don't forget to keep doing your " +
                        "assignments well and according to the time you have set",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color(0xFF4A4646),
                    fontWeight = FontWeight(400),
                    lineHeight = 22.sp,
                ),
                modifier = Modifier
                    .size(320.dp, 100.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.navigate("secondIntroduction") },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF007BFF)
                ),
                modifier = Modifier.size(53.dp),
                contentPadding = PaddingValues(0.dp) // Giảm padding để icon không bị cắt
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(50.dp)
                )
            }

            Button(
                onClick = { },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF007BFF)
                ),
                modifier = Modifier.size(300.dp, 53.dp)
            ) {
                Text(
                    text = "Get Started",
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
fun PreviewWelcomeScreen() {
    Controller()
}

@Composable
fun Controller() {
>>>>>>> 9d053641f3ec785b551849934cd5d928dcd49647
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("firstIntroduction") {
            FirstIntroduction(navController)
        }
        composable("secondIntroduction") {
            SecondIntroduction(navController)
        }
        composable("thirdIntroduction") {
            ThirdIntroduction(navController)
        }
        composable(BottomNavItem.Home.route) { HomeScreen(navController = navController) }
        composable(BottomNavItem.Document.route) { MenuScreen() }
        composable(BottomNavItem.Setting.route) { (SettingScreen()) }
        composable(BottomNavItem.Date.route) { DateScreen() }

        composable("detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: 0
            TaskDetailScreen(navController = navController, taskId = taskId)
        }
    }
}
<<<<<<< HEAD

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    NavigationGraph()
}
=======
>>>>>>> 9d053641f3ec785b551849934cd5d928dcd49647
