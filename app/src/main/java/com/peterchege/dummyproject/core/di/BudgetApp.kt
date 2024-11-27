package com.peterchege.dummyproject.core.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BudgetApp : Application(){

    override fun onCreate() {
        super.onCreate()

    }
}