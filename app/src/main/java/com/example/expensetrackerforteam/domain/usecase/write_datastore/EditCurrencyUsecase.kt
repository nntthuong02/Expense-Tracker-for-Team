package com.example.expensetrackerforteam.domain.usecase.write_datastore

import android.util.Log
import com.example.expensetrackerforteam.domain.repository.DatastoreRepository
import javax.inject.Inject

class EditCurrencyUseCase @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(currency: String) {
        datastoreRepository.writeCurrencyToDataStore(currency)
    }
}