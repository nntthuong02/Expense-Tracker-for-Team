package com.example.expensetrackerforteam.common

import androidx.compose.ui.graphics.Color
import com.example.expensetrackerforteam.R
import com.example.expensetrackerforteam.ui.theme.Blue1
import com.example.expensetrackerforteam.ui.theme.Green1
import com.example.expensetrackerforteam.ui.theme.Red1
import com.example.expensetrackerforteam.ui.theme.Yellow1

enum class ParticipantName(val title: String, val iconRes: Int, val color: Color) {
    DAT("Dat", R.drawable.person_24px, Blue1),
    QUAN("Quan", R.drawable.person_24px, Red1),
    THUONG("Thuong", R.drawable.person_24px, Green1),
    TUAN("Tuan", R.drawable.person_24px, Yellow1)
}