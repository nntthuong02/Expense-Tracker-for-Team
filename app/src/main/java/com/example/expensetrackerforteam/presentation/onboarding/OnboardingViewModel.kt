package com.example.expensetrackerforteam.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetrackerforteam.domain.usecase.write_datastore.EditOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val editOnboardingUseCase: EditOnboardingUseCase
) : ViewModel() {
//    fun saveOnBoardingState(completed: Boolean) {
//        viewModelScope.launch(IO) {
//            editOnboardingUseCase(completed = completed)
//        }
//    }
//    fun onEvent(event: OnboardingEvent){
//        when(event){
//            is OnboardingEvent.EditOnboardingUseCase ->{
//                saveOnBoardingState(true)
//            }
//        }
//    }
//
//    private fun saveOnBoardingState(completed: Boolean) {
//        viewModelScope.launch {
//            editOnboardingUseCase(completed = completed)
//        }
//    }

}