package com.example.expensetrackerforteam.presentation.navigation.components
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensetrackerforteam.ui.theme.ExpenseTrackerForTeamTheme

@ExperimentalAnimationApi
@Composable
fun NavBarItem(item: NavBarItemHolder, onClick: () -> Unit, selected: Boolean) {

    val backgroundColor =
        if (selected) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f) else Color.Transparent
    val contentColor =
        if (selected) MaterialTheme.colorScheme.onSurface else Color.Gray

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Icon(
                item.icon,
                tint = contentColor,
                contentDescription = item.title
            )
//            Spacer(modifier = Modifier.padding(horizontal = 0.dp))
            AnimatedVisibility(visible = selected) {
                Text(
                    text = item.title,
                    textAlign = TextAlign.Center,
                    color = contentColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun NavBarItemPreview() {
    val item = NavBarItemHolder(
        title = "Home",
        icon = Icons.Filled.Home,
        route = "home"
    )
    val selected = remember { mutableStateOf(true) }

    ExpenseTrackerForTeamTheme {
            NavBarItem(
                item = item,
                onClick = { selected.value = !selected.value },
                selected = selected.value
            )
    }
}