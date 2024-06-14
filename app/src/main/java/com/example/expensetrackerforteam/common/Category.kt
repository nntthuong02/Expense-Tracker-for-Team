package com.example.expensetrackerforteam.common

import androidx.compose.ui.graphics.Color
import com.example.expensetrackerforteam.R
import com.example.expensetrackerforteam.ui.theme.Blue1
import com.example.expensetrackerforteam.ui.theme.Yellow1

enum class Category(val title: String, val iconRes: Int, val colorRes: Color, val bgRes: Color) {
    FOOD_DRINK("food_drink", R.drawable.food, Blue1, Yellow1),
    HEALTH("health", R.drawable.person_24px, Blue1, Yellow1),
    WATER_ELECTRICITY("water_electricity", R.drawable.water_electricity, Blue1, Yellow1),
    EDUCATION("education", R.drawable.education, Blue1, Yellow1),
    CLOTHES("clothes", R.drawable.clothes, Blue1, Yellow1),
    TRAVEL("travel", R.drawable.travel, Blue1, Yellow1),
}