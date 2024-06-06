package com.example.expensetrackerforteam.domain.model

import java.util.Date

data class Transaction(
    val date: Date,
    val dateOfEntry: String,
    val amount: Double,
    val participant: String,
    val category: String,
    val transactionType: String,
    val title: String,
)