package com.example.expensetrackerforteam.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.expensetrackerforteam.common.Constants
import com.example.expensetrackerforteam.common.Constants.EXPENSE_TRACKER_KEY
import com.example.expensetrackerforteam.domain.repository.DatastoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = EXPENSE_TRACKER_KEY)
class DatastoreRepositoryImpl @Inject constructor(
    private val context: Context
) : DatastoreRepository {
    private val datastore = context.dataStore
    private val onBoardingKey = booleanPreferencesKey(Constants.ONBOARDING_KEY)
    private val limitKey = booleanPreferencesKey(Constants.LIMIT_KEY)
    private val selectedCurrency = stringPreferencesKey(Constants.CURRENCY_KEY)
    private val expenseLimit = doublePreferencesKey(Constants.EXPENSE_LIMIT_KEY)
    private val limitDuration = intPreferencesKey(Constants.LIMIT_DURATION)
    override suspend fun writeOnboardingKeyToDataStore(completed: Boolean) {
        datastore.edit { store ->
            store[onBoardingKey] = completed
        }
    }

    override suspend fun readOnboardingKeyFromDataStore(): Flow<Boolean> {
        return datastore.data.map { preferences ->
            preferences[onBoardingKey] ?: false
        }
    }

    override suspend fun writeCurrencyToDataStore(currency: String) {
        datastore.edit{ store ->
            store[selectedCurrency] = currency
        }
    }

    override suspend fun readCurrencyFromDataStore(): Flow<String> {
        return datastore.data.map{preferences ->
            preferences[selectedCurrency] ?: String()
        }
    }

    override suspend fun writeExpenseLimitKeyToDataStore(amount: Double) {
        datastore.edit{ store ->
            store[expenseLimit] = amount
        }
    }

    override suspend fun readExpenseLimitFromDataStore(): Flow<Double> {
        return datastore.data.map{ preferences ->
            preferences[expenseLimit] ?: 0.0
        }
    }

    override suspend fun writeLimitKeyToDataStore(enabled: Boolean) {
        datastore.edit{ store ->
            store[limitKey] = enabled
        }
    }

    override suspend fun readLimitKeyFromDataStore(): Flow<Boolean> {
        return datastore.data.map{ preferences ->
            preferences[limitKey] ?: false
        }
    }

    override suspend fun writeLimitDurationToDataStore(duration: Int) {
        datastore.edit { store ->
            store[limitDuration] = duration
        }
    }

    override suspend fun readLimitDurationFromDataStore(): Flow<Int> {
        return datastore.data.map { preferences ->
            preferences[limitDuration] ?: 0
        }
    }

    override suspend fun eraseDataStore() {
        datastore.edit { 
            it.remove(limitKey)
            it.remove(limitDuration)
            it.remove(expenseLimit)
        }
    }
}



