package com.example.expensetrackerforteam.common

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.Color
import com.example.expensetrackerforteam.R
import com.example.expensetrackerforteam.ui.theme.Blue1
import com.example.expensetrackerforteam.ui.theme.Yellow1

enum class TabTransaction (val title: String, val iconRes: Int, val colorRes: Color, val bgRes: Color){
    INCOME_TAB("ADD INCOME", R.drawable.income, Color.White, Color.Red),
    EXPENSE_TAB("ADD EXPENSE", R.drawable.expense, Color.White, Color.Red),
}