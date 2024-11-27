package com.peterchege.dummyproject.ui.screens.budget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.peterchege.dummyproject.core.database.entity.ExpenseEntity
import com.peterchege.dummyproject.ui.components.CustomIconButton

@Composable
fun BudgetScreen(
    navController: NavController,
    viewModel: BudgetScreenViewModel = hiltViewModel(),
) {
    val expenses by viewModel.expenses.collectAsStateWithLifecycle()

    BudgetScreenContent(
        expenses = expenses
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreenContent(
    expenses: List<ExpenseEntity>
) {

    val budget = 200000
    val totalAmountSpent = expenses.sumOf { it.expenseAmount.toInt() }
    val remainingAmount = budget - totalAmountSpent
    val groupedExpenses = expenses.groupBy { it.expenseCategoryName }
    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        sheetContent = {},
        sheetPeekHeight = 0.dp,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Welcome Back. Peter!")
                },
                actions = {
                    CustomIconButton(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "Add",
                        onClick = {

                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            val colors = listOf(0xFF80BA27, 0xFF00513A).map { Color(it) }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(124.dp)
                    .background(brush = Brush.horizontalGradient(colors)),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = remainingAmount.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp,
                        color = Color.White
                    )
                    Text(
                        text = "remaining in your monthly budget",
                        fontWeight = FontWeight.Normal,
                        fontSize = 11.sp,
                        color = Color.White
                    )
                }
            }
            groupedExpenses.map { (category, expenses) ->
                Text(text = "Category $category has ${expenses.size} expenses")
            }
        }
    }

}


@Preview
@Composable
fun BudgetScreenPreview() {
    BudgetScreenContent(
        expenses = emptyList()
    )

}