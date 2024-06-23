package com.example.expensetrackerforteam.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableInferredTarget
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetrackerforteam.common.Category
import com.example.expensetrackerforteam.common.Constants
import com.example.expensetrackerforteam.domain.model.Transaction
import com.example.expensetrackerforteam.presentation.home_screen.HomeViewModel
import com.example.expensetrackerforteam.ui.theme.Green1
import com.example.expensetrackerforteam.ui.theme.Red1
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionItem(
    transaction: Transaction,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onItemClick: () -> Unit
) {
    val category = getCategory(transaction.category)
    val currencyCode by homeViewModel.selectedCurrencyCode.collectAsState()

    Card(
        onClick = { onItemClick() },
        colors = CardDefaults.cardColors(Color.DarkGray.copy(alpha = 0.1f)),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 5.dp,
                    vertical = 5.dp
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = category.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(
                            category.bgRes,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .padding(
                            vertical = 5.dp,
                            horizontal = 10.dp
                        ),
                    color = category.colorRes,
                    letterSpacing = TextUnit(1.1f, TextUnitType.Sp)
                )

                Text(
                    text = transaction.participant,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .padding(
                            vertical = 5.dp,
                            horizontal = 10.dp
                        ),
                    color = Color.Black,
                    letterSpacing = TextUnit(1.1f, TextUnitType.Sp)
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    painter = painterResource(id = category.iconRes),
                    contentDescription = "transaction",
                    tint = Color.Black,
                    modifier = Modifier
                        .background(
                            Color.DarkGray.copy(alpha = 0.2f),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(18.dp)
                )

                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    if (transaction.title.isNotEmpty()) {
                        Text(
                            text = transaction.title,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(5.dp))
                    }

                    Text(
                        text = currencyCode + "${transaction.amount}",
                        color = if (transaction.transactionType == Constants.INCOME)
                            Green1
                        else Red1.copy(alpha = 0.75f),
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W600),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
    Card(
        colors = CardDefaults.cardColors(Color.DarkGray.copy(alpha = 0.1f)),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
    ) {
    }
}

fun getCategory(title: String): Category {
    var result: Category = Category.FOOD_DRINK
    Category.values().forEach {
        if (it.title == title)
            result = it
    }
    return result
}

@Preview(showSystemUi = true)
@Composable
fun TransactionItemPreview(){
    val transaction = Transaction(
        date = Date(),
        dateOfEntry = "Test",
        category = "FOOD_DRINK",
        participant = "Quan",
        title = "Grocery shopping",
        amount = 50.0,
        transactionType = Constants.EXPENSE
    )
    TransactionItem(
        transaction = transaction,
        onItemClick = {}
    )
}