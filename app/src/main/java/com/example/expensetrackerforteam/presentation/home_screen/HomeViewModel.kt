package com.example.expensetrackerforteam.presentation.home_screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetrackerforteam.common.Category
import com.example.expensetrackerforteam.common.Constants
import com.example.expensetrackerforteam.common.ParticipantName
import com.example.expensetrackerforteam.common.TabButton
import com.example.expensetrackerforteam.common.TabTransaction
import com.example.expensetrackerforteam.data.local.entity.TransactionDto
import com.example.expensetrackerforteam.domain.model.Transaction
import com.example.expensetrackerforteam.domain.usecase.GetDateUseCase
import com.example.expensetrackerforteam.domain.usecase.GetFormattedDateUseCase
import com.example.expensetrackerforteam.domain.usecase.read_database.GetAllTransactionUseCase
import com.example.expensetrackerforteam.domain.usecase.read_database.GetDailyTransactionUseCase
import com.example.expensetrackerforteam.domain.usecase.read_database.GetParticipantUseCase
import com.example.expensetrackerforteam.domain.usecase.read_database.GetParticipantsUseCase
import com.example.expensetrackerforteam.domain.usecase.read_datastore.GetCurrencyUseCase
import com.example.expensetrackerforteam.domain.usecase.write_database.InsertNewTransactionUseCase
import com.example.expensetrackerforteam.domain.usecase.write_database.InsertParticipantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val insertNewTransactionUseCase: InsertNewTransactionUseCase,
    private val getDailyTransactionUseCase: GetDailyTransactionUseCase,
    private val getAllTransactionUseCase: GetAllTransactionUseCase,
    private val insertParticipantsUseCase: InsertParticipantsUseCase,
    private val getParticipantUseCase: GetParticipantUseCase,
    private val getParticipantsUseCase: GetParticipantsUseCase,
    private val getFormattedDateUseCase: GetFormattedDateUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val getDateUseCase: GetDateUseCase,
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
    var numberOfTeamT = MutableStateFlow(String())
        private set
    var isPaidT = MutableStateFlow(false)
        private set
    var tabTransaction = MutableStateFlow(TabTransaction.INCOME_TAB)
//    fun String.amountFormat(): String {
//        val amountFormatter = DecimalFormat("#,##0.00")
//        return " " + amountFormatter.format(this.toDouble())
//    }
    private var isDecimal = MutableStateFlow(false)
    private var decimal: String = String()
    var date = MutableStateFlow(String())
        private set
    var currentTime = MutableStateFlow(Calendar.getInstance().time)
        private set

    init{
        val currentDate = getDateUseCase()
        formattedDate.value = getFormattedDateUseCase(currentTime.value)
        date.value = currentDate
        currencyFormat()

    }
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
    fun setNumberOfTeam(number: String){
        numberOfTeamT.value = number
    }
    fun setIsPaid(isPaid: Boolean){
        isPaidT.value = isPaid
    }

    data class TransactionUiState(
        val transactionDetails : Transaction = Transaction(
            Date(), "", 0.0, "", "", "", "", 0, false
        ),
        val isEntryValid: Boolean = false
    )
    fun Transaction.toTransactionDto(): TransactionDto = TransactionDto(
        id = 0,
        date = date,
        dateOfEntry = dateOfEntry,
        amount = amount,
        participant = participant,
        category = category,
        transactionType = transactionType,
        title = title,
        numberOfTeam = numberOfTeam,
        isPaid = isPaid
    )
    fun setCurrentTime(time: Date) {
        currentTime.value = time
    }

    fun insertNewTransaction(
        date: String,
        amount: Double,
        category: String,
        transactionType: String,
        transactionTitle: String,
        numberOfTeam: Int,
        isPaid: Boolean,
        navigateBack: () -> Unit
    ) {
        viewModelScope.launch(IO) {
            if (amount <= 0.0) {
                showInfoBanner.value = true
                delay(2000)
                showInfoBanner.value = false
                return@launch
            }

            val newTransaction = TransactionDto(
                id = 0,
                currentTime.value,
                date,
                amount,
                participant.value.title,
                category,
                transactionType,
                transactionTitle,
                numberOfTeam,
                isPaid
            )
            insertNewTransactionUseCase(newTransaction)

            if (transactionType == Constants.INCOME) {
                val currentParticipant = getParticipantUseCase(participant.value.title).first()
                val newIncomeAmount = currentParticipant.income + amount
                val balance = newIncomeAmount - currentParticipant.expense

                currentParticipant.income = newIncomeAmount
                currentParticipant.balance = balance

                insertParticipantsUseCase(listOf(currentParticipant))
            } else {
                val currentAccount = getParticipantUseCase(participant.value.title).first()
                val newExpenseAmount = currentAccount.expense + amount
                val balance = currentAccount.income - newExpenseAmount

                currentAccount.expense = newExpenseAmount
                currentAccount.balance = balance

                insertParticipantsUseCase(listOf(currentAccount))
            }
            withContext(Main) {
                navigateBack()
            }
        }
    }
    private fun currencyFormat() {
        viewModelScope.launch(IO) {
            getCurrencyUseCase().collect { selectedCurrency ->
                selectedCurrencyCode.value = selectedCurrency
            }
        }
    }
}