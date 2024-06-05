package com.example.expensetrackerforteam.di

import android.app.Application
import android.content.Context
import com.example.expensetrackerforteam.data.repository.DatastoreRepositoryImpl
import com.example.expensetrackerforteam.domain.repository.DatastoreRepository
import com.example.expensetrackerforteam.domain.usecase.AppEntryUseCase
import com.example.expensetrackerforteam.domain.usecase.read_datastore.GetOnboardingKeyUseCase
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
        application: Application
    ) : DatastoreRepository {
        return DatastoreRepositoryImpl(application)
    }

    //Test
    @Provides
    @Singleton
    fun provideAppEntryUseCase(
        datastoreRepository: DatastoreRepository
    ) = AppEntryUseCase(
            getOnboardingKeyUseCase = GetOnboardingKeyUseCase(datastoreRepository),
            editOnboardingUseCase = EditOnboardingUseCase(datastoreRepository)
        )
}