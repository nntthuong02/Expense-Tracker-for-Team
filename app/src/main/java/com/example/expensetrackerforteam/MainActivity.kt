package com.example.expensetrackerforteam

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.expensetrackerforteam.domain.usecase.AppEntryUseCase
import com.example.expensetrackerforteam.presentation.onboarding.OnboardingScreen
import com.example.expensetrackerforteam.presentation.onboarding.OnboardingViewModel
import com.example.expensetrackerforteam.ui.theme.ExpenseTrackerForTeamTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appEntryUseCase: AppEntryUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        lifecycleScope.launch {
            appEntryUseCase.getOnboardingKeyUseCase().collect{
                Log.d("test", it.toString())
            }
        }
        setContent {
            ExpenseTrackerForTeamTheme {
                val viewModel: OnboardingViewModel = hiltViewModel()
                OnboardingScreen(
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpenseTrackerForTeamTheme {
        Greeting("Android")
    }
}