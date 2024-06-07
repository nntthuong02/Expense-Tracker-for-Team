package com.example.expensetrackerforteam.domain.usecase.read_database

import com.example.expensetrackerforteam.data.local.entity.TransactionDto
import com.example.expensetrackerforteam.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionByParticipant @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(participantName: String): Flow<List<TransactionDto>> {
        return transactionRepository.getTransactionByParticipant(participantName)
    }
}