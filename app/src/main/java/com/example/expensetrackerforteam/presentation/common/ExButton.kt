package com.example.expensetrackerforteam.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expensetrackerforteam.presentation.onboarding.OnboardingViewModel

@Composable
fun ExButton(
    text: String,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.Red
        ),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = Color.Red
        )
    }
}

@Composable
fun ExTextButton(
    text: String,
    onClick: () -> Unit,
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = Color.Red
        )
    }
}

// Preview cho NewsButton
@Preview(showBackground = true)
@Composable
fun NewsButtonPreview() {
    MaterialTheme {
        ExButton(text = "Click Me", onClick = {})
    }
}

// Preview cho NewsTextButton
@Preview(showBackground = true)
@Composable
fun NewsTextButtonPreview() {
    MaterialTheme {
        ExTextButton(text = "Click Me", onClick = {})
    }
}