package com.example.expensetrackerforteam.domain.usecase.read_datastore

import android.util.Log
import com.example.expensetrackerforteam.domain.repository.DatastoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOnboardingKeyUseCase @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke() : Flow<Boolean> {
        return datastoreRepository.readOnboardingKeyFromDataStore()
    }

}