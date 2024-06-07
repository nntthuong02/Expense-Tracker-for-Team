package com.example.expensetrackerforteam.presentation.welcome_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetrackerforteam.domain.usecase.read_datastore.GetCurrencyUseCase
import com.example.expensetrackerforteam.domain.usecase.write_datastore.EditCurrencyUseCase
import com.example.expensetrackerforteam.domain.usecase.write_datastore.EditOnboardingUseCase
import com.example.expensetrackerforteam.presentation.onboarding.OnboardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val editOnboardingUseCase: EditOnboardingUseCase,
//    private val editCurrencyUseCase: EditCurrencyUseCase,

//    getCurrencyUseCase: GetCurrencyUseCase
): ViewModel(){
    fun onEvent(event: OnboardingEvent){
        when(event){
            is OnboardingEvent.EditOnboardingUseCase ->{
                saveOnBoardingState(true)
            }
        }
    }
    private fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch {
            editOnboardingUseCase(completed = completed)
        }
    }
}