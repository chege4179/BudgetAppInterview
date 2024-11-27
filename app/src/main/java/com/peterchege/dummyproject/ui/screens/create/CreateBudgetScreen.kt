package com.peterchege.dummyproject.ui.screens.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CreateBudgetScreen(
    navController: NavController,
    viewModel: CreateBudgetScreenViewModel = hiltViewModel()
) {


}


@Composable
fun CreateBudgetScreenContent(
    screenState: FormState,
    onChangeItemName: (String) -> Unit,
    onChangeItemAmount: (String) -> Unit,
    onSubmit: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                label = {
                    Text(text = "Item Name")
                },
                value = screenState.itemName,
                onValueChange = onChangeItemName,
                modifier = Modifier.fillMaxWidth()
            )




        }
    }


}

@Preview
@Composable
fun CreateBudgetScreenPreview() {
    CreateBudgetScreenContent(
        screenState = FormState(),
        onChangeItemName = {},
        onChangeItemAmount = {},
        onSubmit = {}

    )
}