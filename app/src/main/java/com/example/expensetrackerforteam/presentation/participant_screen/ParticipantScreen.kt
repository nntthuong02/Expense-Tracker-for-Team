package com.example.expensetrackerforteam.presentation.participant_screen

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.expensetrackerforteam.presentation.navigation.Route
import com.example.expensetrackerforteam.presentation.participant_screen.components.ParticipantItem

@Composable
fun ParticipantScreen(
    navController: NavHostController,
    participantViewModel: ParticipantViewModel = hiltViewModel()
) {
    val participants by participantViewModel.allParticipants.collectAsState()
    val currency by participantViewModel.selectedCurrencyCode.collectAsState()
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn {
            item {
                Text(
                    text = "Participants",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W700),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 5.dp,
                            end = 5.dp,
                            top = 5.dp
                        ),
                    textAlign = TextAlign.Start
                )

            }
            itemsIndexed(participants) { index, participant ->
                ParticipantItem(participant, currency) { participantName ->
                    Log.d("test lazyColumn", "Click ok")
                    navController.navigate("${Route.ParticipantDetailScreen.route}/$participantName")
                    Log.d("test Route.ParticipantDetailScreen", "$participantName")
                }
            }
        }
    }
}