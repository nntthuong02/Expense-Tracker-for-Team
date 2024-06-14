package com.example.expensetrackerforteam.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.expensetrackerforteam.presentation.navigation.Route
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.expensetrackerforteam.R

@Composable
fun AddEntryChooser(
//    bottomSheetScaffoldState: BottomSheetScaffoldState,
    navController: NavHostController
) {

    val scope = rememberCoroutineScope()
//    val progress = bottomSheetScaffoldState.bottomSheetState.progress.fraction
//    val expandRotation by animateFloatAsState(
//        targetValue = -360f * progress,
//        animationSpec = spring(dampingRatio = 0.75f, stiffness = Spring.StiffnessLow)
//    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
//                    scope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
                    navController.navigate("${Route.TransactionScreen.route}/0")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.income),
                    contentDescription = "add entry",
                    tint = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .size(48.dp, 48.dp)
                        .background(Color.Red)
                        .padding(8.dp)
                )
            }

            Text(
                text = "ADD INCOME",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
//                    scope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
                    navController.navigate("${Route.TransactionScreen.route}/1")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.expense),
                    contentDescription = "expense",
                    tint = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .size(48.dp, 48.dp)
                        .background(Color.Red)
                        .padding(8.dp)
                )
            }

            Text(
                text = "ADD EXPENSE",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddEntryChoosePreview(){
    AddEntryChooser(navController = rememberNavController())
}