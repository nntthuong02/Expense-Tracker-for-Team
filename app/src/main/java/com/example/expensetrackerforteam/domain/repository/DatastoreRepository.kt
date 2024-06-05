package com.example.expensetrackerforteam.domain.repository

import kotlinx.coroutines.flow.Flow

interface DatastoreRepository {
    suspend fun writeOnboardingKeyToDataStore(completed: Boolean)


    suspend fun readOnboardingKeyFromDataStore(): Flow<Boolean>
}