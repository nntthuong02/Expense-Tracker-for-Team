package com.example.expensetrackerforteam.presentation.home_screen.components

import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetrackerforteam.common.ParticipantName
import com.example.expensetrackerforteam.presentation.home_screen.HomeViewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.expensetrackerforteam.R


@Composable
fun ParticipantTag(participantName: ParticipantName, homeViewModel: HomeViewModel = hiltViewModel()) {
    val selectedParticipant by homeViewModel.participant.collectAsState()
    val isSelected = selectedParticipant == participantName

    TextButton(
        onClick = {
            homeViewModel.selectParticipant(participantName)
        },
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(
            horizontal = 5.dp,
            vertical = 5.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            else Color.Transparent,
            contentColor = if (isSelected)
                Color.White else
                MaterialTheme.colorScheme.primary
        ),
    ) {
        Icon(
            painter = painterResource(
                id = if (isSelected)
                    R.drawable.checked else participantName.iconRes
            ),
            contentDescription = participantName.title,
        )

        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = participantName.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ParticipantTagPreview() {
    ParticipantTag(participantName = ParticipantName.THUONG)
}