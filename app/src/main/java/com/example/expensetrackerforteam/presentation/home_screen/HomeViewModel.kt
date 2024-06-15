package com.example.expensetrackerforteam.presentation.home_screen


import androidx.lifecycle.ViewModel
import com.example.expensetrackerforteam.common.Category
import com.example.expensetrackerforteam.common.ParticipantName
import com.example.expensetrackerforteam.common.TabButton
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel(){
    var participant = MutableStateFlow(ParticipantName.THUONG)
        private set

    var category = MutableStateFlow(Category.FOOD_DRINK)
        private set

    var tabButton = MutableStateFlow(TabButton.TODAY)
        private set
    var selectedCurrencyCode = MutableStateFlow(String())
        private set

    fun selectParticipant(participantName: ParticipantName) {
        this.participant.value = participantName
    }
    fun selectCategory(category: Category) {
        this.category.value = category
    }
    fun selectTabButton(button: TabButton) {
        tabButton.value = button
    }
}