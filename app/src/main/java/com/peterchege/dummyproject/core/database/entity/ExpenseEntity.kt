package com.peterchege.dummyproject.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Expenses")
data class ExpenseEntity (
    @PrimaryKey
    val expenseId:String,
    val expenseName:String,
    val expenseAmount:Int,
)