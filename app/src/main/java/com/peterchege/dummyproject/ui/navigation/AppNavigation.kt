package com.peterchege.dummyproject.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.peterchege.dummyproject.ui.screens.budget.BudgetScreen
import com.peterchege.dummyproject.ui.screens.create.CreateBudgetScreen
import com.peterchege.dummyproject.ui.screens.success.SuccessScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavBar(
                items = bottomBarItems,
                navController = navController,
                onItemClick = {
                    if (it.route.isNotBlank()){
                        navController.navigate(it.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }

                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = Screens.BUDGET_SCREEN
            ) {
                composable(route = Screens.BUDGET_SCREEN) {
                    BudgetScreen(navController = navController)
                }
                composable(route = Screens.CREATE_BUDGET_SCREEN) {
                    CreateBudgetScreen(navController = navController)
                }

                composable(route = Screens.SUCCESS_SCREEN) {
                    SuccessScreen(navController = navController)

                }
            }
        }
    }
}


val bottomBarItems = listOf(
    BottomNavItem(
        name = "Home",
        route = Screens.BUDGET_SCREEN,
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
    BottomNavItem(
        name ="Budgets",
        route = "",
        selectedIcon = Icons.Default.DateRange,
        unselectedIcon = Icons.Default.DateRange
    ),

    BottomNavItem(
        name = "Add",
        route = Screens.CREATE_BUDGET_SCREEN,
        selectedIcon = Icons.Default.Add,
        unselectedIcon = Icons.Default.Add
    ),
    BottomNavItem(
        name ="Reports",
        route = "",
        selectedIcon = Icons.Default.Person,
        unselectedIcon = Icons.Outlined.Person
    ),
    BottomNavItem(
        name ="Account",
        route = "",
        selectedIcon = Icons.Default.Person,
        unselectedIcon = Icons.Outlined.Person
    )

)