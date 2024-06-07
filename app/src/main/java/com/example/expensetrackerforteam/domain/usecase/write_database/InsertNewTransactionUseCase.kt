package com.example.expensetrackerforteam.domain.usecase.write_database

import com.example.expensetrackerforteam.data.local.entity.TransactionDto
import com.example.expensetrackerforteam.domain.repository.TransactionRepository
import javax.inject.Inject

class InsertNewTransactionUseCase @Inject constructor(private val repo: TransactionRepository) {

    suspend operator fun invoke(dailyExpense: TransactionDto) {
        repo.insertTransaction(dailyExpense)
    }
}