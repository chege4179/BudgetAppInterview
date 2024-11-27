package com.peterchege.dummyproject.core.di

import android.app.Application
import androidx.room.Room
import com.peterchege.dummyproject.core.database.database.BudgetAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideExpenseTrackerDatabase(app: Application): BudgetAppDatabase {
        return Room.databaseBuilder(
            app,
            BudgetAppDatabase::class.java,
            "budget_db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }
}