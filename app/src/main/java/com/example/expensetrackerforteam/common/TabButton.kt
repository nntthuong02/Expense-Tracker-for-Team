package com.example.expensetrackerforteam.common

import androidx.compose.ui.graphics.Color
import com.example.expensetrackerforteam.R
import com.example.expensetrackerforteam.ui.theme.Blue1
import com.example.expensetrackerforteam.ui.theme.Yellow1

enum class TabButton(val title: String, val colorRes: Color, val bgRes: Color) {
    TODAY("today", Blue1, Yellow1),
    MONTH("month", Blue1, Yellow1),
}