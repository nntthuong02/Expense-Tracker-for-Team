package com.example.expensetrackerforteam.presentation.welcome_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetrackerforteam.common.ParticipantName
import com.example.expensetrackerforteam.data.local.entity.ParticipantDto
import com.example.expensetrackerforteam.domain.model.CurrencyModel
import com.example.expensetrackerforteam.domain.usecase.GetCurrency
import com.example.expensetrackerforteam.domain.usecase.read_datastore.GetCurrencyUseCase
import com.example.expensetrackerforteam.domain.usecase.write_database.InsertParticipantsUseCase
import com.example.expensetrackerforteam.domain.usecase.write_datastore.EditCurrencyUseCase
import com.example.expensetrackerforteam.domain.usecase.write_datastore.EditOnboardingUseCase
import com.example.expensetrackerforteam.presentation.onboarding.OnboardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val editOnboardingUseCase: EditOnboardingUseCase,
    private val editCurrencyUseCase: EditCurrencyUseCase,
    private val insertParticipantsUseCase: InsertParticipantsUseCase,
    getCurrency: GetCurrency

): ViewModel(){
//    fun onEvent(event: OnboardingEvent){
//        when(event){
//            is OnboardingEvent.EditOnboardingUseCase ->{
//                saveOnBoardingState(true)
//            }
//        }
//    }
    fun saveOnboardingState(completed: Boolean) {
        viewModelScope.launch(IO) {
            editOnboardingUseCase(completed = completed)
        }
    }
//    private fun saveOnBoardingState(completed: Boolean) {
//        viewModelScope.launch {
//            editOnboardingUseCase(completed = completed)
//        }
//    }
    fun saveCurrency(currency: String) {
        viewModelScope.launch(IO) {
            editCurrencyUseCase(currency)
        }
    }
    var countryCurrencies = mutableStateOf(emptyMap<Char, List<CurrencyModel>>())
        private set

    init {
        countryCurrencies.value = getCurrency().groupBy { it.country[0] }
        Log.d("test currency", countryCurrencies.value.toString())
    }

    fun createParticipants() {
        viewModelScope.launch(IO) {
            insertParticipantsUseCase.invoke(
                listOf(
                    ParticipantDto(1, ParticipantName.DAT.title, 0.0, 0.0, 0.0),
                    ParticipantDto(2, ParticipantName.QUAN.title, 0.0, 0.0, 0.0),
                    ParticipantDto(3, ParticipantName.THUONG.title, 0.0, 0.0, 0.0),
                    ParticipantDto(4, ParticipantName.TUAN.title, 0.0, 0.0, 0.0)
                )
            )
        }
    }
}