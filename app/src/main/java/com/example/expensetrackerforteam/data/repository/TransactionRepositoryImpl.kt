package com.example.expensetrackerforteam.data.repository

import com.example.expensetrackerforteam.data.TransactionDao
import com.example.expensetrackerforteam.data.local.entity.ParticipantDto
import com.example.expensetrackerforteam.data.local.entity.TransactionDto
import com.example.expensetrackerforteam.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
): TransactionRepository{
    override suspend fun insertTransaction(dailyExpenseTracker: TransactionDto) {
        transactionDao.insertTransaction(dailyExpenseTracker)
    }

    override suspend fun insertParticipants(participant: List<ParticipantDto>) {
        transactionDao.insertParticipants(participant)
    }

    override fun getDailyTransaction(entryDate: String): Flow<List<TransactionDto>> {
        return transactionDao.getDailyTransaction(entryDate)
    }

    override fun getTransactionByParticipant(participantName: String): Flow<List<TransactionDto>> {
        return transactionDao.getTransactionByParticipant(participantName)
    }

    override fun getParticipant(participant: String): Flow<ParticipantDto> {
        return transactionDao.getParticipant(participant)
    }

    override fun getParticipants(): Flow<List<ParticipantDto>> {
        return transactionDao.getParticipants()
    }

    override fun getAllTransaction(): Flow<List<TransactionDto>> {
        return transactionDao.getAllTransaction()
    }

    override fun eraseTransaction() {
        transactionDao.eraseTransaction()
    }

    override fun getCurrentDayExpTransaction(): Flow<List<TransactionDto>> {
        return transactionDao.getCurrentDayExpTransaction()
    }

    override fun getWeeklyExpTransaction(): Flow<List<TransactionDto>> {
        return transactionDao.getWeeklyExpTransaction()
    }

    override fun getMonthlyExpTransaction(): Flow<List<TransactionDto>> {
        return transactionDao.getMonthlyExpTransaction()
    }

    override fun get3DayTransaction(transactionType: String): Flow<List<TransactionDto>> {
        return transactionDao.get3DayTransaction(transactionType)
    }

    override fun get7DayTransaction(transactionType: String): Flow<List<TransactionDto>> {
        return transactionDao.get7DayTransaction(transactionType)
    }

    override fun get14DayTransaction(transactionType: String): Flow<List<TransactionDto>> {
        return transactionDao.get14DayTransaction(transactionType)
    }

    override fun getStartOfMonthTransaction(transactionType: String): Flow<List<TransactionDto>> {
        return transactionDao.getStartOfMonthTransaction(transactionType)
    }

    override fun getLastMonthTransaction(transactionType: String): Flow<List<TransactionDto>> {
        return transactionDao.getLastMonthTransaction(transactionType)
    }

    override fun getTransactionByType(transactionType: String): Flow<List<TransactionDto>> {
        return transactionDao.getTransactionByType(transactionType)
    }

}