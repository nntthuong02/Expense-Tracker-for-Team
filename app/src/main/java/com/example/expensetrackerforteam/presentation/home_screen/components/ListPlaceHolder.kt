package com.example.expensetrackerforteam.presentation.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expensetrackerforteam.R

@Composable
fun ListPlaceHolder(
    label: String =
        "No transaction has been made so far. Tap the '+' button to  get started"
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(
            start = 5.dp,
            top = 5.dp,
            end = 5.dp
        ).fillMaxSize()
    ) {
        Icon(
            painter = painterResource(R.drawable.list_alt_24px),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = "no item added"
        )

        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ListPlaceHolderPreview(){
    ListPlaceHolder()
}