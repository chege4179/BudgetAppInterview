package com.peterchege.dummyproject.ui.screens.create

import androidx.compose.animation.fadeOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.room.util.query
import com.peterchege.dummyproject.core.util.Category
import com.peterchege.dummyproject.core.util.categories
import com.peterchege.dummyproject.ui.components.CustomIconButton
import com.peterchege.dummyproject.ui.components.GenericDropDownMenu
import com.peterchege.dummyproject.ui.navigation.Screens
import kotlinx.coroutines.launch

@Composable
fun CreateBudgetScreen(
    navController: NavController,
    viewModel: CreateBudgetScreenViewModel = hiltViewModel()
) {
    val screenState by viewModel.formState.collectAsStateWithLifecycle()
    var isSuccess by remember {
        mutableStateOf(false)
    }

    CreateBudgetScreenContent(
        screenState = screenState,
        onChangeItemName = viewModel::onChangeItemName,
        onChangeItemAmount = viewModel::onChangeItemAmount,
        onChangeItemCategory = viewModel::onChangeItemCategory,
        onSubmit = {
            viewModel.onSubmit(
                onSuccess = { expense ->
                    isSuccess = true
                    navController.currentBackStackEntry?.savedStateHandle?.set("item", expense)
                    navController.navigate(Screens.SUCCESS_SCREEN)
                }
            )
        }
    )


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBudgetScreenContent(
    screenState: FormState,
    onChangeItemName: (String) -> Unit,
    onChangeItemAmount: (String) -> Unit,
    onChangeItemCategory: (Category) -> Unit,
    onSubmit: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    )


    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContainerColor = Color.LightGray,
        sheetContent = {
            GenericDropDownMenu<Category>(
                dropDownTitle = "Categories",
                items = categories,
                selectedOption = screenState.itemCategory,
                onClick = {
                    onChangeItemCategory(it)
                    scope.launch {
                        scaffoldState.bottomSheetState.hide()
                    }
                },
                filterFunction = { query, items -> items },
                title = { it.categoryName }
            )

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp),
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
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                label = {
                    Text(text = "Item Amount")
                },
                value = screenState.itemAmount,
                onValueChange = onChangeItemAmount,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                label = {
                    Text(text = "Item Category")
                },
                value = screenState.itemCategory?.categoryName ?: "Select A Category",
                onValueChange = {

                },
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    CustomIconButton(
                        modifier = Modifier.size(45.dp),
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Drop Down",
                        onClick = {
                            scope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        },
                    )
                },
                interactionSource = remember { MutableInteractionSource() }
                    .also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    scope.launch {
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            }
                        }
                    },
            )

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
                onClick = onSubmit,
                enabled = screenState.itemName.isNotBlank() && screenState.itemAmount.isNotBlank(),
            ) {
                Text(text = "Submit")
            }
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
        onChangeItemCategory = {},
        onSubmit = {}

    )
}