package com.peterchege.dummyproject.ui.screens.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterchege.dummyproject.core.database.database.BudgetAppDatabase
import com.peterchege.dummyproject.core.database.entity.ExpenseEntity
import com.peterchege.dummyproject.core.util.Category
import com.peterchege.dummyproject.core.util.isSpecialCharString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class FormState(
    val itemName: String = "",
    val itemAmount: String = "",
    val itemCategory: Category? = null,
)

@HiltViewModel
class CreateBudgetScreenViewModel @Inject constructor(
    private val database: BudgetAppDatabase
) : ViewModel() {


    private val _formState = MutableStateFlow(FormState())
    val formState = _formState.asStateFlow()


    fun onChangeItemName(name: String) {
        _formState.update { it.copy(itemName = name) }
    }

    fun onChangeItemAmount(name: String) {
        if (name.isSpecialCharString()) return
        _formState.update { it.copy(itemAmount = name) }
    }

    fun onChangeItemCategory(category: Category) {
        _formState.update { it.copy(itemCategory = category) }
    }

    fun onSubmit(
        onSuccess: (ExpenseEntity) -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val expense = ExpenseEntity(
                    expenseAmount = _formState.value.itemAmount,
                    expenseName = _formState.value.itemName,
                    expenseCategoryName = _formState.value.itemCategory?.categoryName ?: ""
                )
                database.expenseEntityDao.insertExpense(
                    expenseEntity = expense
                )
                _formState.update {
                    it.copy(
                        itemName = "",
                        itemAmount = "",
                        itemCategory = null
                    )
                }
                onSuccess(expense)
            } catch (e: Exception) {
                e.printStackTrace()

            }

        }
    }


}