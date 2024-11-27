package com.peterchege.dummyproject.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.peterchege.dummyproject.core.database.dao.ExpenseEntityDao
import com.peterchege.dummyproject.core.database.entity.ExpenseEntity

@Database(
    entities = [
        ExpenseEntity::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class BudgetAppDatabase : RoomDatabase() {

    abstract val expenseEntityDao: ExpenseEntityDao


}