package com.example.expensetrackerforteam.presentation.home_screen.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetrackerforteam.common.Category
import com.example.expensetrackerforteam.presentation.home_screen.HomeViewModel
import androidx.compose.material3.TextButton
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.expensetrackerforteam.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Category(
    expenseItems: Array<Category> = Category.values()
) {
    FlowRow(
        modifier = Modifier.padding(
            start = 5.dp,
            top = 5.dp,
            bottom = 5.dp,
        ),
    ) {
        expenseItems.forEach {
            CategoryTag(category = it)
        }
    }
}


@Composable
fun CategoryTag(category: Category, homeViewModel: HomeViewModel = hiltViewModel()) {
    val selected by homeViewModel.category.collectAsState()
    var isSelected = selected.title == category.title
    TextButton(
        modifier = Modifier.padding(end = 5.dp),
        onClick = {
            homeViewModel.selectCategory(category)
            isSelected = selected.title == category.title
        },
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(
            horizontal = 5.dp,
            vertical = 5.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) {
                category.bgRes.copy(alpha = 0.95f)
            } else MaterialTheme.colorScheme.surface,
            contentColor = if (isSelected) {
                category.colorRes
            } else MaterialTheme.colorScheme.onSurface
        ),
    ) {
        Icon(
            painter = if (!isSelected) {
                painterResource(id = R.drawable.add)
            } else painterResource(id = category.iconRes),
            contentDescription = category.title,
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = category.title,
            style = MaterialTheme.typography.titleSmall
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoryPreview() {
    Category()
}