package com.example.expensetrackerforteam.presentation.home_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expensetrackerforteam.common.ParticipantName
import com.example.expensetrackerforteam.common.TransactionType
import com.example.expensetrackerforteam.presentation.home_screen.components.AddTransactionChooser
import com.example.expensetrackerforteam.presentation.home_screen.components.Category
import com.example.expensetrackerforteam.presentation.home_screen.components.InfoBanner
import com.example.expensetrackerforteam.presentation.home_screen.components.ParticipantTag

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    navController: NavController,
    transactionTag: Int?,
    transactionDate: String?,
    transactionPos: Int?,
    transactionStatus: Int?,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val transactionType = TransactionType.values()[transactionTag!!]
    val scope = rememberCoroutineScope()
    val title by remember { mutableStateOf(homeViewModel.transactionTitle) }
    val titleFieldValue = TextFieldValue(title.collectAsState().value)
    val showInfoBanner by homeViewModel.showInfoBanner.collectAsState()
    val transaction by remember { mutableStateOf(homeViewModel.transactionAmount) }
    val transactionFieldValue = TextFieldValue(transaction.collectAsState().value)
    val expenseAmount by homeViewModel.transactionAmount.collectAsState()
    val currencyCode by homeViewModel.selectedCurrencyCode.collectAsState()
    //
    val selectedParticipantName by homeViewModel.participant.collectAsState()
    val participantList: Array<ParticipantName> = ParticipantName.values()
    Column(
        modifier = Modifier
        .fillMaxSize()) {
        AddTransactionChooser()
        Spacer(modifier = Modifier.padding(5.dp))
        InfoBanner(shown = showInfoBanner, transactionType)
        TextField(
            value = titleFieldValue.text,
            onValueChange = { field ->
                homeViewModel.setTransactionTitle(field)

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 5.dp,
                    top = 5.dp,
                    end = 5.dp,
                    bottom = 5.dp
                ),
            maxLines = 1,
            singleLine = true,
            placeholder = {
                Text(
                    text = if (transactionType == TransactionType.INCOME)
                        "Income title"
                    else "Expense title",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600)
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
                containerColor = Color.LightGray
            )
        )

        Text(
            text = if (transactionType == TransactionType.INCOME) {
                "Income of"
            } else "Expense of",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(
                    horizontal = 5.dp,
                    vertical = 5.dp
                )
                .align(Alignment.Start)
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 5.dp
                )
        )
        //ParticipantTag
        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = 5.dp,
                    vertical = 5.dp
                )
                .align(Alignment.Start)
        ) {
            items(ParticipantName.values()) { participant ->
                ParticipantTag(participantName = participant)
            }
        }
        //Transaction amount
        TextField(
            value = transactionFieldValue.text,
            onValueChange = { field ->
                Log.d("Test", "field ok")
                homeViewModel.setTransaction(field)
                Log.d("test", "setTransaction Ok")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 5.dp,
                    top = 5.dp,
                    end = 5.dp,
                    bottom = 5.dp
                ),
            maxLines = 1,
            singleLine = true,
            placeholder = {
                Text(
                    text = "Enter the amount"
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
                containerColor = Color.LightGray
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
        )
        //
        Text(
            text = "Set category"
        )
        Category()
    }

}

@Preview(showSystemUi = true)
@Composable
fun TransactionScreenPreview(){
    TransactionScreen(
        navController = rememberNavController(),
        transactionTag = 1,
        transactionDate = "1",
        transactionPos = 1,
        transactionStatus = 1
    )
}