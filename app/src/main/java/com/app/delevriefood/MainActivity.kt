package com.app.delevriefood

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.app.delevriefood.model.OnboardingItem
import com.app.delevriefood.ui.screen.OnboardingScreen
import com.app.delevriefood.ui.theme.DelevrieFoodTheme

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

        setContent {
            DelevrieFoodTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    OnboardingScreen(
                        onboardingItems = onboardingItems,
                        onFinish = {
                            Toast.makeText(
                                this@MainActivity,
                                "Onboarding finished!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }
    }
}