package com.peterchege.dummyproject.ui.screens.budget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.peterchege.dummyproject.core.util.categories
import com.peterchege.dummyproject.core.util.formatCurrency
import com.peterchege.dummyproject.core.util.safeToDouble
import com.peterchege.dummyproject.ui.components.CustomIconButton
import com.peterchege.dummyproject.ui.components.HomeSubHeader
import com.peterchege.dummyproject.ui.components.Slice
import com.peterchege.dummyproject.ui.components.StackedBar
import com.peterchege.dummyproject.ui.components.TransactionCard

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

    val slices = remember(expenses) {
        categories.map { category ->
            val expenseAmount = expenses.filter { it.expenseCategoryName == category.categoryName }
                .sumOf { it.expenseAmount.toInt() }
            val value = ((expenseAmount.toDouble() / budget) * 100).toFloat()
            println("Value : ${value}")
            Slice(
                value = value,
                color = category.color,
                text = formatCurrency(amount = expenseAmount.toString().safeToDouble(), "KES")
            )
        } + Slice(
            color = Color.Black,
            text = "Remaining\n Amount",
            value = ((remainingAmount.toDouble() / budget) * 100).toFloat()
        )
    }
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
                .verticalScroll(rememberScrollState())
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
                        text = formatCurrency(remainingAmount.toString().safeToDouble(), "KES"),
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
            Text(
                text = "Total Spend This Month",
                fontWeight = FontWeight.Normal,
                fontSize = 11.sp,
                color = Color.Black
            )
            Text(
                text = formatCurrency(totalAmountSpent.toString().safeToDouble(), "KES"),
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = Color.Black
            )
            StackedBar(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(10.dp)),
                slices = slices
            )
            categories.map { category ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(category.color)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = category.categoryName,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = formatCurrency(
                            amount = expenses.filter { it.expenseCategoryName == category.categoryName }
                                .sumOf { it.expenseAmount.toInt() }.toString().safeToDouble(),
                            currencyCode = "KES"
                        )
                    )
                }
            }

            HomeSubHeader(
                name = "Recent Transactions",
                onClick = {

                }
            )
            expenses.takeLast(3).map { expense ->
                TransactionCard(transaction = expense, onTransactionNavigate = {})
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