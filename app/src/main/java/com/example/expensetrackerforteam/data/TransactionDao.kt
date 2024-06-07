package com.example.expensetrackerforteam.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.expensetrackerforteam.common.TransactionType
import com.example.expensetrackerforteam.data.local.entity.ParticipantDto
import com.example.expensetrackerforteam.data.local.entity.TransactionDto
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParticipants(participants: List<ParticipantDto>)

    @Query("SELECT * FROM transaction_table WHERE entry_date = :entryDate")
    fun getDailyTransaction(entryDate: String) : Flow<List<TransactionDto>>

    @Query("SELECT * FROM transaction_table WHERE participant = :participantName")
    fun getTransactionByParticipant(participantName: String): Flow<List<TransactionDto>>

    @Query("SELECT * FROM participant_table WHERE participant = :participant")
    fun getParticipant(participant: String) : Flow<ParticipantDto>

    @Query("SELECT * FROM participant_table")
    fun getParticipants() : Flow<List<ParticipantDto>>

    @Query("SELECT * FROM transaction_table")
    fun getAllTransaction() : Flow<List<TransactionDto>>

    @Query("DELETE FROM transaction_table")
    fun eraseTransaction()

    @Query("SELECT * FROM transaction_table WHERE entry_date = date('now', 'localtime') AND transaction_type = :transactionType")
    fun getCurrentDayExpTransaction(transactionType: String = TransactionType.EXPENSE.title): Flow<List<TransactionDto>>

    @Query("SELECT * FROM transaction_table WHERE entry_date BETWEEN date('now', '-7 day') AND date('now', 'localtime') AND transaction_type = :transactionType")
    fun getWeeklyExpTransaction(transactionType: String = TransactionType.EXPENSE.title): Flow<List<TransactionDto>>

    @Query("SELECT * FROM transaction_table WHERE entry_date BETWEEN date('now', '-1 month') AND date('now', 'localtime') AND transaction_type = :transactionType")
    fun getMonthlyExpTransaction(transactionType: String = TransactionType.EXPENSE.title): Flow<List<TransactionDto>>

    @Query("SELECT * FROM transaction_table WHERE entry_date >= date('now', '-3 day') AND entry_date < date('now', 'localtime') AND transaction_type = :transactionType")
    fun get3DayTransaction(transactionType: String): Flow<List<TransactionDto>>

    @Query("SELECT * FROM transaction_table WHERE entry_date >= date('now', '-7 day') AND entry_date < date('now', 'localtime') AND transaction_type = :transactionType")
    fun get7DayTransaction(transactionType: String): Flow<List<TransactionDto>>

    @Query("SELECT * FROM transaction_table WHERE entry_date >= date('now', '-14 day') AND entry_date < date('now', 'localtime') AND transaction_type = :transactionType")
    fun get14DayTransaction(transactionType: String): Flow<List<TransactionDto>>

    @Query("SELECT * FROM transaction_table WHERE entry_date >= date('now', 'start of month') AND entry_date < date('now', 'localtime') AND transaction_type = :transactionType")
    fun getStartOfMonthTransaction(transactionType: String): Flow<List<TransactionDto>>

    @Query("SELECT * FROM transaction_table WHERE entry_date >= date('now', '-1 month') AND entry_date < date('now', 'localtime') AND transaction_type = :transactionType")
    fun getLastMonthTransaction(transactionType: String): Flow<List<TransactionDto>>

    @Query("SELECT * FROM transaction_table WHERE transaction_type = :transactionType")
    fun getTransactionByType(transactionType: String): Flow<List<TransactionDto>>
}
