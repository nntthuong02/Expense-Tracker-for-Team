package com.example.expensetrackerforteam.presentation.participant_screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetrackerforteam.presentation.home_screen.components.TransactionItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ParticipantDetailScreen(participantName: String?, participantViewModel: ParticipantViewModel = hiltViewModel()) {
    //transactions is empty
    val transactions by participantViewModel.transactions.collectAsState()
    //participantName is null
    if (participantName != null) {
        participantViewModel.getTransaction(participantName)
        Log.d("test participantName", "$participantName")
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(
            top = 5.dp
        )
    ) {
        transactions.ifEmpty {
            Text(text = "Not Transaction")
            Log.d("test transactions", "ifEmpty")
        }

        LazyColumn(
            contentPadding = PaddingValues(
                start = 5.dp,
                top = 5.dp,
                end = 5.dp
            )
        ) {
            item {
                Text(
                    text = "Transactions",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Normal),
                )

                Text(
                    text = participantName!!,
                    style = MaterialTheme.typography.displayMedium.copy(fontSize = 20.sp, fontWeight = FontWeight.W700),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Log.d("test ParticipantDetail", "test1")
            transactions.forEach { (date, alltransaction) ->
                Log.d("test ParticipantDetail", "test2")
                stickyHeader {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(
                                vertical = 5.dp
                            ),
                    ) {
                        Text(
                            text = date,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                    }
                }

                itemsIndexed(alltransaction) { index, transaction ->

                    TransactionItem(
                        transaction = transaction,
                        onItemClick = {
                            Log.d("test click TransactionItem", "Click ok")
                        }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ParticipantDetailScreenPreview(){
    ParticipantDetailScreen(participantName = "Thuong")
}