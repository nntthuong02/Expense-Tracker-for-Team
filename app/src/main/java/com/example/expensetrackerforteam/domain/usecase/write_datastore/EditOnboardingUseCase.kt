package com.example.expensetrackerforteam.domain.usecase.write_datastore

import com.example.expensetrackerforteam.domain.repository.DatastoreRepository
import javax.inject.Inject

class EditOnboardingUseCase @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(completed: Boolean) {
        datastoreRepository.writeOnboardingKeyToDataStore(completed)
    }
}