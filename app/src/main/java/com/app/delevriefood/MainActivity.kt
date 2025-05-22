package com.app.delevriefood

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.app.delevriefood.data.OnboardingItem
import com.app.delevriefood.ui.screen.OnboardingScreen
import com.app.delevriefood.ui.screen.SignInScreen
import com.app.delevriefood.ui.screen.SignUpScreen
import com.app.delevriefood.ui.theme.DelevrieFoodTheme
import com.app.delevriefood.ui.viewModel.AuthState
import com.app.delevriefood.ui.viewModel.AuthViewModel
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val onboardingItems = listOf(
            OnboardingItem(
                image = R.drawable.onboarding_1,
                title = "Préparez-vous à un festin visuel et gustatif avec notre menu interactif !"
            ),
            OnboardingItem(
                image = R.drawable.onboarding_2,
                title = "Bienvenue dans le monde délicieux SirFood, où chaque plat raconte une histoire"
            ),
            OnboardingItem(
                image = R.drawable.onboarding_3,
                title = "Préparez-vous à un festin visuel et gustatif avec notre menu interactif !"
            )
        )

        @Composable
                fun HideSystemBars() {
                    val systemUiController = rememberSystemUiController()
                    SideEffect {
                        systemUiController.isStatusBarVisible = false
                        systemUiController.setSystemBarsColor(
                            color = Color.Transparent,
                            darkIcons = false
                        )
                    }
                }


        setContent {
            DelevrieFoodTheme {
                // Hide system bars
                HideSystemBars()
                
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val authViewModel: AuthViewModel = viewModel()
                    val authState by authViewModel.authState.collectAsState()
                    var currentScreen by remember { mutableStateOf(Screen.Onboarding) }
                    
                    // Check if user is already authenticated
                    LaunchedEffect(authState) {
                        if (authState is AuthState.Authenticated) {
                            // Navigate to home screen or main content
                            Toast.makeText(
                                this@MainActivity,
                                "Authenticated! Welcome back.",
                                Toast.LENGTH_SHORT
                            ).show()
                            // You would typically navigate to your main app content here
                        }
                    }
                    
                    when (currentScreen) {
                        Screen.Onboarding -> {
                            OnboardingScreen(
                                onboardingItems = onboardingItems,
                                onFinish = {
                                    currentScreen = Screen.SignIn
                                }
                            )
                        }
                        Screen.SignIn -> {
                            SignInScreen(
                                authViewModel = authViewModel,
                                onSignIn = {
                                    // This will be called when authentication is successful
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Sign in successful!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    // Navigate to home screen or main content
                                },
                                onCreateAccount = {
                                    currentScreen = Screen.SignUp
                                }
                            )
                        }
                        Screen.SignUp -> {
                            SignUpScreen(
                                authViewModel = authViewModel,
                                onSignUp = {
                                    // This will be called when registration is successful
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Account created successfully!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    // Navigate to home screen or main content
                                },
                                onSignIn = {
                                    currentScreen = Screen.SignIn
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class Screen {
    Onboarding, SignIn, SignUp
}