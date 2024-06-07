package com.example.expensetrackerforteam.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.expensetrackerforteam.data.TransactionDao
import com.example.expensetrackerforteam.data.TransactionDatabase
import com.example.expensetrackerforteam.data.repository.DatastoreRepositoryImpl
import com.example.expensetrackerforteam.data.repository.TransactionRepositoryImpl
import com.example.expensetrackerforteam.domain.repository.DatastoreRepository
import com.example.expensetrackerforteam.domain.repository.TransactionRepository
import com.example.expensetrackerforteam.domain.usecase.AppEntryUseCase
import com.example.expensetrackerforteam.domain.usecase.read_datastore.GetCurrencyUseCase
import com.example.expensetrackerforteam.domain.usecase.read_datastore.GetOnboardingKeyUseCase
import com.example.expensetrackerforteam.domain.usecase.write_datastore.EditCurrencyUseCase
import com.example.expensetrackerforteam.domain.usecase.write_datastore.EditOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExpenseModule {

    //error: [Dagger/MissingBinding] android.content.Context cannot be provided without an @Provides-annotated method.
    //Debug: use Application instead of Context ???
    @Provides
    @Singleton
    fun provideDatastoreRepository(
        applicaion: Application
    ) : DatastoreRepository {
        return DatastoreRepositoryImpl(applicaion)
    }

    @Provides
    @Singleton
    fun provideExpenseRepository(transactionDao: TransactionDao) : TransactionRepository
            = TransactionRepositoryImpl(transactionDao)

    @Provides
    @Singleton
    fun provideTransactioDatabase(context: Context): TransactionDatabase{
        return Room.databaseBuilder(context, TransactionDatabase::class.java, "transactionDb")
            .fallbackToDestructiveMigration()
            .build()
    }

    //Test
    @Provides
    @Singleton
    fun provideAppEntryUseCase(
        datastoreRepository: DatastoreRepository
    ) = AppEntryUseCase(
            getOnboardingKeyUseCase = GetOnboardingKeyUseCase(datastoreRepository),
            editOnboardingUseCase = EditOnboardingUseCase(datastoreRepository),
            editCurrencyUseCase = EditCurrencyUseCase(datastoreRepository)
        )


}