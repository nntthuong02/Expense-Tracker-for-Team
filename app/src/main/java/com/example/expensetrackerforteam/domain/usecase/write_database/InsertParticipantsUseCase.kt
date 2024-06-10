package com.example.expensetrackerforteam.domain.usecase.write_database

import com.example.expensetrackerforteam.data.local.entity.ParticipantDto
import com.example.expensetrackerforteam.domain.repository.TransactionRepository
import javax.inject.Inject

class InsertParticipantsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(participant: List<ParticipantDto>) {
        transactionRepository.insertParticipants(participant)
    }
}