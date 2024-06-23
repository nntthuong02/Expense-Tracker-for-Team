package com.example.expensetrackerforteam.presentation.home_screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.expensetrackerforteam.presentation.navigation.Route
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.expensetrackerforteam.R
import com.example.expensetrackerforteam.common.TabButton
import com.example.expensetrackerforteam.common.TabTransaction
import com.example.expensetrackerforteam.presentation.home_screen.HomeViewModel
import com.example.expensetrackerforteam.ui.theme.Blue1

@Composable
fun AddTransactionChooser(
//    bottomSheetScaffoldState: BottomSheetScaffoldState,
    tabs: Array<TabTransaction> = TabTransaction.values(),
    onButtonClick: () -> Unit = { },
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    val selectedTransaction by homeViewModel.tabTransaction.collectAsState()
//    val progress = bottomSheetScaffoldState.bottomSheetState.progress.fraction
//    val expandRotation by animateFloatAsState(
//        targetValue = -360f * progress,
//        animationSpec = spring(dampingRatio = 0.75f, stiffness = Spring.StiffnessLow)
//    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        tabs.forEach { tab ->
            val backgroundColor by animateColorAsState(
                if (selectedTransaction == tab) Color.Transparent
                else Color.Red
            )

            val textColor by animateColorAsState(
                if (selectedTransaction == tab) MaterialTheme.colorScheme.surface
                else MaterialTheme.colorScheme.onSurface,
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = {
                        homeViewModel.selectTabTransaction(tab)
                        onButtonClick()
                    },
                    colors = IconButtonDefaults.iconButtonColors(Blue1)
                    ) {
                    Icon(
                        painter = painterResource(id = tab.iconRes),
                        contentDescription = "add entry",
                        tint = textColor,
                        modifier = Modifier
                            .size(48.dp, 48.dp)
                            .background(backgroundColor)
                            .padding(8.dp)
                    )

                }
                Text(
                    text = tab.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddEntryChoosePreview(){
    AddTransactionChooser()
}