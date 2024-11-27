package com.peterchege.dummyproject.ui.screens.success

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.peterchege.dummyproject.R
import com.peterchege.dummyproject.core.database.entity.ExpenseEntity
import com.peterchege.dummyproject.core.util.formatCurrency
import com.peterchege.dummyproject.core.util.getTodaysDate
import com.peterchege.dummyproject.core.util.safeToDouble
import com.peterchege.dummyproject.core.util.toast
import com.peterchege.dummyproject.ui.components.SuccessSummaryRow

@Composable
fun SuccessScreen(
    navController: NavController,
    expenseItem: ExpenseEntity

) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),

        ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Image(
                painter = painterResource(R.drawable.success),
                contentDescription = "Success",
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .height(100.dp)
            )

            SuccessSummaryRow(
                expenseName = "Budget Item Name",
                expenseAmount = expenseItem.expenseName
            )
            SuccessSummaryRow(
                expenseName = "Amount",
                expenseAmount = formatCurrency(expenseItem.expenseAmount.safeToDouble(), "KES")
            )
            SuccessSummaryRow(
                expenseName = "Category",
                expenseAmount = expenseItem.expenseCategoryName
            )
            SuccessSummaryRow(expenseName = "Date", expenseAmount = getTodaysDate())

            Spacer(modifier = Modifier.weight(1f))
            Button(
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.primary,
                    disabledContainerColor = colorScheme.surfaceDim
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = navController::navigateUp,
                enabled = true,
            ) {
                Text(text = "Go Back To Home")
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorScheme.primary,
                    disabledContainerColor = colorScheme.surfaceDim
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    context.toast(msg = "Glad you are enjoying the app")
                },
                enabled = true,
            ) {
                Text(text = "Rate your experience")
            }


        }

    }

}


@Composable
fun SuccessScreenContent(

) {

}