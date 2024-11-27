package com.peterchege.dummyproject.ui.screens.create

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class FormState(
    val itemName:String = "",
    val itemAmount:String = "",
)

@HiltViewModel
class CreateBudgetScreenViewModel @Inject constructor(

) :ViewModel(){


    private val _formState = MutableStateFlow(FormState())
    val formState = MutableStateFlow(FormState())


    fun onChangeItemName(name:String){
        _formState.update { it.copy(itemName = name) }
    }

    fun onChangeItemAmount(name:String){
        _formState.update { it.copy(itemAmount = name) }
    }



}