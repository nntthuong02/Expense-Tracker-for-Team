package com.example.expensetrackerforteam.presentation.home_screen


import androidx.lifecycle.ViewModel
import com.example.expensetrackerforteam.common.Category
import com.example.expensetrackerforteam.common.ParticipantName
import com.example.expensetrackerforteam.common.TabButton
import com.example.expensetrackerforteam.common.TabTransaction
import com.example.expensetrackerforteam.common.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.DecimalFormat
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
    var formattedDate = MutableStateFlow(String())
        private set
    var totalIncome = MutableStateFlow(0.0)
        private set
    var totalExpense = MutableStateFlow(0.0)
        private set
    var transactionTitle = MutableStateFlow(String())
        private set
    var showInfoBanner = MutableStateFlow(false)
        private set
    var transactionAmount = MutableStateFlow(String())
        private set
    var tabTransaction = MutableStateFlow(TabTransaction.INCOME_TAB)
//    fun String.amountFormat(): String {
//        val amountFormatter = DecimalFormat("#,##0.00")
//        return " " + amountFormatter.format(this.toDouble())
//    }
    private var isDecimal = MutableStateFlow(false)
    private var decimal: String = String()
    fun selectParticipant(participantName: ParticipantName) {
        this.participant.value = participantName
    }
    fun selectCategory(category: Category) {
        this.category.value = category
    }
    fun selectTabButton(button: TabButton) {
        tabButton.value = button
    }
    fun selectTabTransaction(button: TabTransaction){
        tabTransaction.value = button
    }
    fun setTransactionTitle(title: String) {
        transactionTitle.value = title
    }
    //Doc lai
    fun setTransaction(amount: String) {
        transactionAmount.value = amount
    }

}