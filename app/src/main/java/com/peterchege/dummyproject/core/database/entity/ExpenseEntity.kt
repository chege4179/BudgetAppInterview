package com.peterchege.dummyproject.core.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Expenses")
data class ExpenseEntity (
    @PrimaryKey(autoGenerate = true)
    val expenseId:Int? = null,
    val expenseName:String,
    val expenseAmount:String,
    val expenseCategoryName:String,
):Parcelable