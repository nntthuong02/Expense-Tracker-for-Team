package com.example.expensetrackerforteam.presentation.home_screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun HomeScreen(
    navController: NavController
) {
    TransactionScreen(
        navController = rememberNavController(),
        transactionTag = 1,
        transactionDate = "date",
        transactionPos = 1,
        transactionStatus = 1
    )
}


