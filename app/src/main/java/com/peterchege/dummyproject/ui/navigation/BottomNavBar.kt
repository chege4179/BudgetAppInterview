package com.peterchege.dummyproject.ui.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@ExperimentalMaterial3Api
@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit,
) {

    val backStackEntry = navController.currentBackStackEntryAsState()
    NavigationBar {
        items.forEachIndexed { index, item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.name
                    )
                },
                label = {
                    Text(
                        text = item.name,
                        fontSize = 11.5.sp
                    )
                },

                selected = selected,
                onClick = { onItemClick(item) }
            )
        }
    }


}