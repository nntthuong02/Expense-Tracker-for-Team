package com.example.expensetrackerforteam.domain.repository

import com.example.expensetrackerforteam.data.local.entity.ParticipantDto
import com.example.expensetrackerforteam.data.local.entity.TransactionDto
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun insertTransaction(dailyExpenseTracker: TransactionDto)

    suspend fun insertParticipants(participant: List<ParticipantDto>)

    fun getDailyTransaction(entryDate: String) : Flow<List<TransactionDto>>

    fun getTransactionByParticipant(participantName: String): Flow<List<TransactionDto>>

    fun getParticipant(participant: String): Flow<ParticipantDto>

    fun getParticipants(): Flow<List<ParticipantDto>>

    fun getAllTransaction() : Flow<List<TransactionDto>>

    fun eraseTransaction()

    fun getCurrentDayExpTransaction(): Flow<List<TransactionDto>>

    fun getWeeklyExpTransaction(): Flow<List<TransactionDto>>

    fun getMonthlyExpTransaction(): Flow<List<TransactionDto>>

    fun get3DayTransaction(transactionType: String): Flow<List<TransactionDto>>

    fun get7DayTransaction(transactionType: String): Flow<List<TransactionDto>>

    fun get14DayTransaction(transactionType: String): Flow<List<TransactionDto>>

    fun getStartOfMonthTransaction(transactionType: String): Flow<List<TransactionDto>>

    fun getLastMonthTransaction(transactionType: String): Flow<List<TransactionDto>>

    fun getTransactionByType(transactionType: String): Flow<List<TransactionDto>>
}