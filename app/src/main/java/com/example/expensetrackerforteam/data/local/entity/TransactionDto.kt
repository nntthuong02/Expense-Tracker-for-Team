package com.example.expensetrackerforteam.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetrackerforteam.domain.model.Transaction
import java.util.Date

@Entity(tableName = "transaction_table")
data class TransactionDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "timestamp")
    val date: Date,
    @ColumnInfo(name = "entry_date")
    val dateOfEntry: String,
    @ColumnInfo(name = "amount")
    val amount: Double,
    @ColumnInfo(name = "participant")
    val participant: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "transaction_type")
    val transactionType: String,
    @ColumnInfo(name = "transaction_title")
    val title: String,
    @ColumnInfo(name = "number_of_team")
    val numberOfTeam: Int,
    @ColumnInfo(name = "is_paid")
    val isPaid: Boolean
) {
    fun toTransaction() = Transaction(date, dateOfEntry, amount, participant, category, transactionType, title, numberOfTeam, isPaid)
}
