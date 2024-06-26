package com.example.expensetrackerforteam

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph
import com.example.expensetrackerforteam.data.repository.DatastoreRepositoryImpl
import com.example.expensetrackerforteam.domain.usecase.AppEntryUseCase
import com.example.expensetrackerforteam.domain.usecase.GetCurrency
import com.example.expensetrackerforteam.presentation.main.MainViewModel
import com.example.expensetrackerforteam.presentation.navigation.MainScreen
import com.example.expensetrackerforteam.presentation.onboarding.OnboardingScreen
import com.example.expensetrackerforteam.presentation.onboarding.OnboardingViewModel
import com.example.expensetrackerforteam.ui.theme.ExpenseTrackerForTeamTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalComposeUiApi
@InternalCoroutinesApi
@AndroidEntryPoint
@ExperimentalUnitApi
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    /*************Test*********/
    @Inject
    lateinit var appEntryUseCase: AppEntryUseCase
//    @Inject
//    lateinit var getCurrency: GetCurrency
    /*************Test*********/
    @Inject
    lateinit var mainViewModel: MainViewModel

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        /*************Test*********/
//        lifecycleScope.launch {
//            appEntryUseCase.getOnboardingKeyUseCase().collect{
//                Log.d("test UseCase", it.toString())
//            }
//        }
//        val currencies = getCurrency.invoke()
//        currencies.forEach {
//            Log.d("CurrencyInfo", it.toString())
//        }
        /*************Test*********/
        setContent {
            ExpenseTrackerForTeamTheme {
//                val viewModel: OnboardingViewModel = hiltViewModel()
                val destination by mainViewModel.startDestination.collectAsState()
                MainScreen(
                    startDestination = destination,
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