package com.example.expensetrackerforteam.domain.usecase.write_database

import com.example.expensetrackerforteam.domain.repository.TransactionRepository
import javax.inject.Inject

class EraseTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke() {
        transactionRepository.eraseTransaction()
    }
}