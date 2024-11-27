package com.peterchege.dummyproject.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peterchege.dummyproject.core.database.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseEntityDao {

    @Query("SELECT * FROM Expenses")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM Expenses WHERE expenseId =:expenseId")
    suspend fun getExpenseById(expenseId:String):ExpenseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expenseEntity: ExpenseEntity)

    @Query("DELETE FROM Expenses WHERE expenseId = :id")
    suspend fun deleteExpenseById(id: String)

    @Query("DELETE FROM Expenses")
    suspend fun deleteAllExpenseCategories()

}