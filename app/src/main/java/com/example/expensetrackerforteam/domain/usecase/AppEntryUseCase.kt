package com.example.expensetrackerforteam.domain.usecase

import com.example.expensetrackerforteam.domain.usecase.read_datastore.GetOnboardingKeyUseCase
import com.example.expensetrackerforteam.domain.usecase.write_datastore.EditOnboardingUseCase

data class AppEntryUseCase(
    val getOnboardingKeyUseCase: GetOnboardingKeyUseCase,
    val editOnboardingUseCase: EditOnboardingUseCase
)
