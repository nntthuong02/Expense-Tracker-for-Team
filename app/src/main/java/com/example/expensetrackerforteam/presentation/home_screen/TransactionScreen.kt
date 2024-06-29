package com.example.expensetrackerforteam.presentation.home_screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import com.example.expensetrackerforteam.common.Constants
import com.example.expensetrackerforteam.common.ParticipantName
import com.example.expensetrackerforteam.common.TabTransaction
import com.example.expensetrackerforteam.common.TransactionType
import com.example.expensetrackerforteam.presentation.home_screen.components.AddTransactionChooser
import com.example.expensetrackerforteam.presentation.home_screen.components.Category
import com.example.expensetrackerforteam.presentation.home_screen.components.InfoBanner
import com.example.expensetrackerforteam.presentation.home_screen.components.ParticipantTag
import com.example.expensetrackerforteam.presentation.navigation.Route
import java.util.Calendar

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
    val selectedTransaction by homeViewModel.tabTransaction.collectAsState()
    val transactionType = TransactionType.values()[transactionTag!!]
    val scope = rememberCoroutineScope()
    val title by remember { mutableStateOf(homeViewModel.transactionTitle) }
    val titleFieldValue = TextFieldValue(title.collectAsState().value)
    val showInfoBanner by homeViewModel.showInfoBanner.collectAsState()
    val transaction by remember { mutableStateOf(homeViewModel.transactionAmount) }
    val transactionFieldValue = TextFieldValue(transaction.collectAsState().value)
    val expenseAmount by homeViewModel.transactionAmount.collectAsState()
    val currencyCode by homeViewModel.selectedCurrencyCode.collectAsState()
    val number by remember { mutableStateOf(homeViewModel.numberOfTeamT) }
    val numberFieldValue = TextFieldValue(number.collectAsState().value)
    val isPaid by remember { mutableStateOf(homeViewModel.isPaidT) }
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
                    text = if (selectedTransaction == TabTransaction.INCOME_TAB)
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
        Log.d("Test TransactionType", "$selectedTransaction")
        Text(
            text = if (selectedTransaction == TabTransaction.INCOME_TAB) {
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

        //Number of team
        TextField(
            value = numberFieldValue.text,
            onValueChange = { field ->
                Log.d("Test", "field ok")
                homeViewModel.setNumberOfTeam(field)
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
                    text = "Enter the number of Team"
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
        Button(
            onClick = {
                      homeViewModel.apply {
                          setCurrentTime(Calendar.getInstance().time)
                          if(transactionType == TransactionType.INCOME){
                              insertNewTransaction(
                                  date.value,
                                  transactionAmount.value.toDouble(),
                                  category.value.title,
                                  Constants.INCOME,
                                  transactionTitle.value,
                                  numberOfTeamT.value.toInt(),
                                  isPaidT.value
                              ){
                                  Log.d("test income transaction", "income success")
                                  navController.navigateUp()
//                                  navController.navigate("${Route.HomeScreen.route}")
                              }
                          } else {
                              insertNewTransaction(
                                  date.value,
                                  transactionAmount.value.toDouble(),
                                  category.value.title,
                                  Constants.EXPENSE,
                                  transactionTitle.value,
                                  numberOfTeamT.value.toInt(),
                                  isPaidT.value
                              ) {
                                  Log.d("test expense transaction", "expense success")
                                  navController.navigateUp()
//                                  navController.navigate("${Route.HomeScreen.route}")
                              }
                          }
                      }
                homeViewModel.setTransactionTitle("")
                homeViewModel.setTransaction("")
//                navController.navigate("${Route.HomeScreen.route}")
                //xu ly xoa het du lieu dang hien thi sau khi nhan "Save"
            },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {

            Text(text = "Save")
        }
    }

}

fun showToast(context: Context?, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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