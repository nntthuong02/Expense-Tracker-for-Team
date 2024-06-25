package com.example.expensetrackerforteam.presentation.participant_screen

import android.text.format.DateFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetrackerforteam.domain.model.Participant
import com.example.expensetrackerforteam.domain.model.Transaction
import com.example.expensetrackerforteam.domain.usecase.read_database.GetParticipantsUseCase
import com.example.expensetrackerforteam.domain.usecase.read_database.GetTransactionByParticipant
import com.example.expensetrackerforteam.domain.usecase.read_datastore.GetCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ParticipantViewModel @Inject constructor(
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val getParticipantsUseCase: GetParticipantsUseCase,
    private val getTransactionByParticipant: GetTransactionByParticipant
): ViewModel() {
    var transactions = MutableStateFlow(mapOf<String, List<Transaction>>())
        private set
    var allParticipants = MutableStateFlow(emptyList<Participant>())
        private set
    var selectedCurrencyCode = MutableStateFlow(String())
        private set

    init {
        currencyFormat()
        viewModelScope.launch(IO){
            getParticipantsUseCase().collect{ participantsDto ->
                val participants = participantsDto.map {
                    it.toParticipant()
                }
                allParticipants.value = participants
            }
        }
    }
    fun getFormattedDate(date: Date): String {
        val dayOfWeek = DateFormat.format("EEE", date)
        val day = DateFormat.format("dd", date)
        val monthAbbr = DateFormat.format("MMM", date)

        return "$dayOfWeek $day, $monthAbbr"
    }
    fun getTransaction(participantName: String){
        viewModelScope.launch(IO) {
            getTransactionByParticipant(participantName).collect{
                it.let{ listTransDto ->
                    val newTrans = listTransDto.map{ transDto ->
                        transDto.toTransaction()
                    }.reversed()
                    transactions.value = newTrans.groupBy { trans ->
                        getFormattedDate(trans.date)
                    }
                }
            }
        }
    }
    private fun currencyFormat(){
        viewModelScope.launch(IO) {
            getCurrencyUseCase().collect{ selectedCurrency ->
                val currencyCode = selectedCurrency
                selectedCurrencyCode.value = currencyCode
            }
        }
    }

}