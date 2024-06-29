package com.example.expensetrackerforteam.domain.model

data class Participant(
    val participant: String,
    val amount: Double,
    val income: Double,
    val expense: Double
)