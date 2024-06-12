package com.example.expensetrackerforteam.presentation.home_screen


import androidx.lifecycle.ViewModel
import com.example.expensetrackerforteam.common.ParticipantName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel(){
    var participant = MutableStateFlow(ParticipantName.THUONG)
        private set

    fun selectParticipant(participantName: ParticipantName) {
        this.participant.value = participantName
    }
}